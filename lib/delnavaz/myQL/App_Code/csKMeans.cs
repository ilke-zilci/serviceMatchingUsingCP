using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.IO;
using System.Text.RegularExpressions;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;


    public class csKMeans
    {
        public csCluster RetrieveData(string address)
        {
            csCluster Cluster = new csCluster();
            string[] StrIntervals;
            Regex regexData = new Regex(" ");
            StreamReader sr = new StreamReader(@address);
            string tmpBuffer = sr.ReadToEnd().Replace("\r", "").Replace("\n", " ");
            StrIntervals = regexData.Split(tmpBuffer);
            sr.Close();
            sr.Dispose();

            Cluster = ReadData(StrIntervals);

            return Cluster;
        }

        public csCluster ReadData(string[] dataCollection)
        {
            csCluster cluster = new csCluster();
            Regex regexInterval = new Regex(",");
            string[] strInterval;

            csDataCluster dataCluster = new csDataCluster();
            for (int i = 0; i < dataCollection.Length; i++)
            {
                double[] arrIntervals = new double[2];
                strInterval = regexInterval.Split(dataCollection[i]);        
                arrIntervals[0] = Convert.ToDouble(strInterval[0]);  
                arrIntervals[1] = Convert.ToDouble(strInterval[1]);
                dataCluster.Add(arrIntervals);
            }
            cluster.Add(dataCluster);
            return cluster;
        }

        public csCluster kMeans(csCluster scatteredClusters, int kValue)
        {
            Random rnd = new Random();
            csCluster centroid = new csCluster();
            List<int> prototypeIndex = new List<int>();
            csCluster cluster = new csCluster();
            csDataCluster dataCluster = new csDataCluster();
            int tmpRand = 0;

            //scattering data
            for (int i = 0; i < scatteredClusters.Count; i++)
                for (int j = 0; j < ((csDataCluster)(scatteredClusters[i])).Count; j++)
                {
                    dataCluster = new csDataCluster();
                    dataCluster.Add((double[])((csDataCluster)(scatteredClusters[i]))[j]);
                    cluster.Add(dataCluster);
                }
            scatteredClusters = (csCluster)(CloneObject((csCluster)(cluster)));


            //clustering similar data
            int c1 = 0, c2;
            while (c1 < cluster.Count)
            {
                c2 = c1 + 1;
                while (c2 < cluster.Count)
                {
                    if (Distance(cluster[c1][0], cluster[c2][0]) == 0)
                    {
                        cluster[c1].Add((double[])(CloneObject((double[])(cluster[c2][0]))));
                        cluster.RemoveRange(cluster.IndexOf(cluster[c2]), 1);
                    }
                    else
                        c2++;
                }
                c1++;
            }

            //choose random points
            if (cluster.Count != 1 && cluster.Count != kValue)
            {
                while (prototypeIndex.Count < kValue)
                {
                    do tmpRand = rnd.Next(cluster.Count);
                    while (prototypeIndex.Contains(tmpRand) || tmpRand >= cluster.Count);
                    prototypeIndex.Add(tmpRand);
                }

                for (int i = 0; i < prototypeIndex.Count; i++)
                {
                    centroid.Add(new csDataCluster());
                    centroid[centroid.Count - 1].Add((double[])(CloneObject((double[])(cluster[prototypeIndex[i]][0]))));
                }



                List<int[]> clustersIndex = new List<int[]>();
                csCluster gatheredClusters = new csCluster();
                double minimumDistance = 0, tmpDistance = 0, distanceSum = 0, oldDistanceSum = 0, elderDistanceSum = 0;
                int minimumDistanceIndex = 0, counter = 0;


                //refinement
                if (scatteredClusters.Count != 1 && cluster.Count != centroid.Count)
                {
                    do
                    {
                        if (gatheredClusters.Count > 0)
                        {
                            clustersIndex.RemoveRange(0, clustersIndex.Count);
                            centroid = (csCluster)(CloneObject((csCluster)(GetClustersCentre(gatheredClusters))));
                            elderDistanceSum = oldDistanceSum;
                            oldDistanceSum = distanceSum;
                            distanceSum = 0;
                            gatheredClusters.RemoveRange(0, gatheredClusters.Count);
                        }
                        for (int i = 0; i < centroid.Count; i++)
                            gatheredClusters.Add(new csDataCluster());

                        for (int i = 0; i < cluster.Count; i++)
                        {
                            for (int j = 0; j < centroid.Count; j++)
                            {
                                tmpDistance = Distance(cluster[i][0], centroid[j][0]);
                                if (tmpDistance < minimumDistance || j == 0)
                                {
                                    minimumDistance = tmpDistance;
                                    minimumDistanceIndex = j;
                                }
                            }
                            gatheredClusters[minimumDistanceIndex].Add((double[])CloneObject((double[])cluster[i][0]));
                            clustersIndex.Add(new int[] { minimumDistanceIndex, gatheredClusters[minimumDistanceIndex].Count - 1 });
                            minimumDistance = 0;
                            tmpDistance = 0;
                            minimumDistanceIndex = 0;
                        }

                        //------ checking if any cluster containing 0 data//----
                        foreach (csDataCluster datacluster in gatheredClusters)
                            if (dataCluster.Count == 0)
                            {
                                dataCluster.Add((double[])(CloneObject((double[])(gatheredClusters[clustersIndex[clustersIndex.Count - 1][0]][clustersIndex[clustersIndex.Count - 1][1]]))));
                                gatheredClusters[clustersIndex[clustersIndex.Count - 1][0]].RemoveRange(clustersIndex[clustersIndex.Count - 1][1], 1);
                                clustersIndex.RemoveRange(clustersIndex.Count - 1, 1);
                            }


                        for (int i = 0; i < centroid.Count; i++)
                            for (int j = 0; j < gatheredClusters[i].Count; j++)
                                distanceSum = distanceSum + Math.Pow(Distance(centroid[i][0], gatheredClusters[i][j]), 2);

                    } while (distanceSum != oldDistanceSum || elderDistanceSum != oldDistanceSum);

                }
                scatteredClusters = (csCluster)(CloneObject((csCluster)(gatheredClusters)));
            }



            //providing clusters' information
            csDataCluster clustersCentre = new csDataCluster();
            double distancetoDatasetsMean = 0, sumofDataClusterDistance = 0, totalSumofDataClusterDistance = 0;

            csDataCluster datasetsMean = new csDataCluster();
            datasetsMean = GetDatasetsInitialCentre(scatteredClusters);

            for (int i = 0; i < scatteredClusters.Count; i++)
            {
                double tmpDistance = 0, minDistance = -1, secMinDistance = -1, thrMinDistance = -1; ;
                int minDistanceIndex = -1, secMinDistanceIndex = -1, thrMinDistanceIndex = -1;

                clustersCentre = (csDataCluster)(CloneObject((csDataCluster)(GetClustersCentre(scatteredClusters[i]))));
                scatteredClusters[i].DataClusterCentre = (double[])(CloneObject((double[])(clustersCentre[0])));
                scatteredClusters[i].Size = scatteredClusters[i].Count;

                for (int j = 0; j < scatteredClusters[i].Count; j++)
                {
                    tmpDistance = Math.Pow(Distance(scatteredClusters[i][j], clustersCentre[0]), 2);

                    if (tmpDistance < minDistance || minDistance == -1)
                    {
                        thrMinDistance = secMinDistance;
                        thrMinDistanceIndex = secMinDistanceIndex;
                        secMinDistance = minDistance;
                        secMinDistanceIndex = minDistanceIndex;

                        minDistanceIndex = j;
                        minDistance = tmpDistance;
                    }
                    else
                    {
                        if (tmpDistance < secMinDistance || secMinDistance == -1)
                        {
                            thrMinDistance = secMinDistance;
                            thrMinDistanceIndex = secMinDistanceIndex;

                            secMinDistanceIndex = j;
                            secMinDistance = tmpDistance;
                        }
                        else
                        {
                            if (tmpDistance < thrMinDistance || thrMinDistance == -1)
                            {
                                thrMinDistanceIndex = j;
                                thrMinDistance = tmpDistance;
                            }
                        }
                    }
                    sumofDataClusterDistance = sumofDataClusterDistance + tmpDistance;
                    distancetoDatasetsMean = distancetoDatasetsMean + Distance(scatteredClusters[i][j], datasetsMean[0]);
                }
                scatteredClusters[i].QualityofDataCluster = 1 - (sumofDataClusterDistance / distancetoDatasetsMean);
                scatteredClusters[i].MinDistance = minDistance;

                if (minDistanceIndex != -1)
                    scatteredClusters[i].ClosestIndividualToCentre =
                        (double[])(CloneObject((double[])(scatteredClusters[i][minDistanceIndex])));
                if (secMinDistanceIndex != -1)
                    scatteredClusters[i].SecClosestIndividualToCentre =
                        (double[])(CloneObject((double[])(scatteredClusters[i][secMinDistanceIndex])));
                if (thrMinDistanceIndex != -1)
                    scatteredClusters[i].ThrClosestIndividualToCentre =
                        (double[])(CloneObject((double[])(scatteredClusters[i][thrMinDistanceIndex])));
                totalSumofDataClusterDistance = totalSumofDataClusterDistance + sumofDataClusterDistance;

                sumofDataClusterDistance = 0;
            }
            scatteredClusters.QualityofPartition = 1 - (totalSumofDataClusterDistance / distancetoDatasetsMean);


            return scatteredClusters;
        }


        public csDataCluster GetDatasetsInitialCentre(csCluster cluster)
        {
            csDataCluster meanDataCluster = new csDataCluster();
            double[] meanCollection = new double[2];

            for (int i = 0; i < cluster.Count; i++)
            {
                for (int j = 0; j < cluster[i].Count; j++)
                {
                    meanCollection[0] = meanCollection[0] + cluster[i][j][0];
                    meanCollection[1] = meanCollection[1] + cluster[i][j][1];
                }
            }
            meanCollection[0] = meanCollection[0] / cluster.NumberofData;
            meanCollection[1] = meanCollection[1] / cluster.NumberofData;

            meanDataCluster.Add((double[])(CloneObject((double[])(meanCollection))));
            //meanCluster.Add((csDataCluster)(CloneObject((csDataCluster)(meanDataCluster))));

            return meanDataCluster;
        }
        
        public double Distance(double[] firstVector, double[] secondVector)
        {
            double distance = 0;
            distance = Math.Sqrt(Math.Pow(secondVector[0] - firstVector[0], 2) +
                        Math.Pow(secondVector[1] - firstVector[1], 2));
            
            return distance;
        }

        public csCluster ScatterData(csCluster selectedCluster)
        {
            csCluster cluster = new csCluster();
            csDataCluster dataCluster = new csDataCluster();
            for (int i = 0; i < selectedCluster.Count; i++)
                for (int j = 0; j < ((csDataCluster)(selectedCluster[i])).Count; j++)
                {
                    dataCluster = new csDataCluster();
                    dataCluster.Add((double[])((csDataCluster)(selectedCluster[i]))[j]);
                    cluster.Add(dataCluster);
                }
            selectedCluster = (csCluster)(CloneObject((csCluster)(cluster)));
            return selectedCluster;
        }

        public object CloneObject(object obj)
        {
            using (MemoryStream memStream = new MemoryStream())
            {
                BinaryFormatter binaryFormatter = new BinaryFormatter(null, new StreamingContext(StreamingContextStates.Clone));
                binaryFormatter.Serialize(memStream, obj);
                memStream.Seek(0, SeekOrigin.Begin);
                return (object)binaryFormatter.Deserialize(memStream);
            }
        }

        public void WriteData(csCluster cluster, string address)
        {
            StreamWriter sw;

            string path = System.IO.Path.GetDirectoryName(address);

            int i = 1;
            foreach (csDataCluster dataCluster in cluster)
            {
                string tmpDataCluster = "";
                sw = new StreamWriter(path + "\\Cluster_" + (i++).ToString() + ".txt");
                for (int j = 0; j < dataCluster.Count; j++)
                    tmpDataCluster = tmpDataCluster + dataCluster[j][0].ToString() + ", " + dataCluster[j][1].ToString() + "\r\n";
                sw.Write(tmpDataCluster);
                sw.Close();
                sw.Dispose();
            }
        }

        #region protected functions

        protected csCluster GetClustersCentre(csCluster cluster)
        {
            csCluster meanCluster = new csCluster();

            for (int i = 0; i < cluster.Count; i++)
            {
                double[] meanVector = new double[2];
                for (int j = 0; j < cluster[i].Count; j++)
                {
                    meanVector[0] = meanVector[0] + cluster[i][j][0] / cluster[i].Count;
                    meanVector[1] = meanVector[1] + cluster[i][j][1] / cluster[i].Count;
                }
                csDataCluster meanDataCluster = new csDataCluster();
                double[] meanCollection = new double[2];
                meanCollection = meanVector;
                meanDataCluster.Add((double[])(CloneObject((double[])(meanCollection))));
                meanCluster.Add((csDataCluster)(CloneObject((csDataCluster)(meanDataCluster))));
            }
            return meanCluster;
        }

        public csDataCluster GetClustersCentre(csDataCluster dataCluster)
        {
            csDataCluster meanDataCluster = new csDataCluster();
            double[] meanInterval = new double[2];
                for (int j = 0; j < dataCluster.Count; j++)
                {
                    meanInterval[0] = meanInterval[0] + dataCluster[j][0];
                    meanInterval[1] = meanInterval[1] + dataCluster[j][1];
                }
                meanInterval[0] = meanInterval[0] / dataCluster.Count;
                meanInterval[1] = meanInterval[1] / dataCluster.Count;

                meanInterval[0] = ((meanInterval[0].ToString("#####.##") != "") ?
                    Convert.ToDouble(meanInterval[0].ToString("#####.##")) : meanInterval[0]);

                meanInterval[1] = ((meanInterval[1].ToString("#####.##") != "") ?
                    Convert.ToDouble(meanInterval[1].ToString("#####.##")) : meanInterval[1]);

            meanDataCluster.Add((double[])(CloneObject((double[])(meanInterval))));

            return meanDataCluster;

        }

        #endregion
    }

