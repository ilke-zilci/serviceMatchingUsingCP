using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

    [Serializable]
    public class csCluster : List<csDataCluster>
    {
        public csCluster()
        { }
        public csCluster(csCluster cluster)
        {

        }

        private int _vectorLength;
        private double _minLowerBound;
        private double _maxUpperBound;
        private int _numberofPartitions;
        private Int16 _metric;
        ////private bool _isInitial;
        private int _numberofData;
        private double _qualityofPartition = 0;
        private TimeSpan _runTimeDuration;


        public double QualityofPartition
        {
            get { return _qualityofPartition; }
            set { _qualityofPartition = value; }
        }
        public int VectorLength
        {
            get { return _vectorLength; }
            set { _vectorLength = value; }
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

        public static csCluster Clone(csCluster Prototype)
        {
            return (csCluster)Prototype.MemberwiseClone();
        }

        public int NumberofPartitions
        {
            get { return _numberofPartitions; }
            set { _numberofPartitions = value; }
        }

        public Int16 Metric
        {
            get { return _metric; }
            set { _metric = value; }
        }

        public int NumberofData
        {
            get { return _numberofData; }
            set { _numberofData = value; }
        }

        public TimeSpan RunTimeDuration
        {
            get { return _runTimeDuration; }
            set { _runTimeDuration = value; }
        }
    }





