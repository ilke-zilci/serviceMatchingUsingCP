using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.IO;

    [Serializable]
    public class csDataCluster : List<double[]>
    {
        private double _minLowerBound;
        private double _maxUpperBound;
        private int _dataClusterIndex = 0;
        private double[] _dataClusterCentre;
        private int _size = 0;
        private double _minDistance = 0;
        private double[] _closestIndividualToCentre;
        private double[] _secClosestIndividualToCentre;
        private double[] _thrClosestIndividualToCentre;
        private double _qualityofDataCluster;

        public double[] DataClusterCentre
        {
            get { return _dataClusterCentre; }
            set { _dataClusterCentre = value; }
        }

        public double MinLowerBound
        {
            get { return _minLowerBound; }
            set { _minLowerBound = value; }
        }

        public double MaxUpperBound
        {
            get { return _maxUpperBound; }
            set { _maxUpperBound = value; }
        }

        public int DataClusterIndex
        {
            get { return _dataClusterIndex; }
            set { _dataClusterIndex = value; }
        }

        public int Size
        {
            get { return _size; }
            set { _size = value; }
        }

        public double MinDistance
        {
            get { return _minDistance; }
            set { _minDistance = value; }
        }

        public double[] ClosestIndividualToCentre
        {
            get { return _closestIndividualToCentre; }
            set { _closestIndividualToCentre = value; }
        }

        public double[] SecClosestIndividualToCentre
        {
            get { return _secClosestIndividualToCentre; }
            set { _secClosestIndividualToCentre = value; }
        }

        public double[] ThrClosestIndividualToCentre
        {
            get { return _thrClosestIndividualToCentre; }
            set { _thrClosestIndividualToCentre = value; }
        }

        public double QualityofDataCluster
        {
            get { return _qualityofDataCluster; }
            set { _qualityofDataCluster = value; }
        }

        //*********************************
        private int _parentIndex = 0;
        public int ParentIndex
        {
            get { return _parentIndex; }
            set { _parentIndex = value; }
        }

        private int _iterationNumber = 0;
        public int IterationNumber
        {
            get { return _iterationNumber; }
            set { _iterationNumber = value; }
        }



    }


