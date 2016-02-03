using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

public partial class step3 : System.Web.UI.Page
{
    public csCluster cluster = new csCluster();
    public csCluster initialCluster = new csCluster();

    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack)
        {
            Browse();
        }
    }

    public csCluster GetClustersSummery(csCluster Cluster)
    {
        double clusterMinLowerBound = -1;
        double clusterMaxUpperBaound = -1;

        for (int i = 0; i < Cluster.Count; i++)
        {
            double minLowerBound = -1;
            double maxUpperBaound = -1;

            for (int z = 0; z < Cluster[i].Count; z++)
            {
                if (((double[])(((csDataCluster)(Cluster[i]))[z]))[0] < minLowerBound || minLowerBound == -1)
                {
                    minLowerBound = Cluster[i][z][0];
                    if (minLowerBound < clusterMinLowerBound || clusterMinLowerBound == -1)
                        clusterMinLowerBound = minLowerBound;
                }
                if (((double[])(((csDataCluster)(Cluster[i]))[z]))[1] > maxUpperBaound || maxUpperBaound == -1)
                {
                    maxUpperBaound = Cluster[i][z][0];
                    if (maxUpperBaound > clusterMaxUpperBaound || clusterMaxUpperBaound == -1)
                        clusterMaxUpperBaound = maxUpperBaound;
                }
            }
            Cluster[i].MinLowerBound = (minLowerBound);
            Cluster[i].MaxUpperBound = (maxUpperBaound);
        }

        return Cluster;
    }
    protected void trvChildClusters_SelectedNodeChanged(object sender, EventArgs e)
    {
        csCluster newCluster = new csCluster();
        newCluster = (csCluster)Session["cluster"];
        try
        {
           TreeNode TN = new TreeNode();
            if(trvChildClusters.Nodes.Count != 0)
            {

                TN = trvChildClusters.SelectedNode; 
                int selectedItemIndex = Convert.ToInt32(trvChildClusters.Nodes.IndexOf(TN));
                    
                txtResult.Text = newCluster[0].DataClusterCentre[0].ToString();
                string strCentroid = "", strFirstClosestIndividual = "", strSecondClosestIndividual = "", strThirdClosestIndividual = "";

                strCentroid = strCentroid + " (" +
                    newCluster[selectedItemIndex].DataClusterCentre[0] + ", " +
                    newCluster[selectedItemIndex].DataClusterCentre[1] + ") ";

                if (newCluster[selectedItemIndex].ClosestIndividualToCentre != null)
                    strFirstClosestIndividual = strFirstClosestIndividual + " (" +
                        newCluster[selectedItemIndex].ClosestIndividualToCentre[0] + ", " +
                        newCluster[selectedItemIndex].ClosestIndividualToCentre[1] + ") ";

                if (newCluster[selectedItemIndex].SecClosestIndividualToCentre != null)
                    strSecondClosestIndividual = strSecondClosestIndividual + " (" +
                       newCluster[selectedItemIndex].SecClosestIndividualToCentre[0] + ", " +
                       newCluster[selectedItemIndex].SecClosestIndividualToCentre[1] + ") ";

                if (newCluster[selectedItemIndex].ThrClosestIndividualToCentre != null)
                    strThirdClosestIndividual = strThirdClosestIndividual + " (" +
                        newCluster[selectedItemIndex].ThrClosestIndividualToCentre[0] + ", " +
                        newCluster[selectedItemIndex].ThrClosestIndividualToCentre[1] + ") ";

                txtClustersDetails.Text = "  Cluster's Centre: " + strCentroid + "\r\n";
                txtClustersDetails.Text += "  First Closest individual to Centre: " +
                    strFirstClosestIndividual + "\r\n";
                txtClustersDetails.Text +=  "  Second Closest individual to Centre: " + strSecondClosestIndividual + "\r\n";
                txtClustersDetails.Text +=  "  Third Closest individual to Centre: " +
                    strThirdClosestIndividual + "\r\n";
                txtClustersDetails.Text +=  "  Size: " + newCluster[selectedItemIndex].Size.ToString() + "\r\n";
            }

            txtResult.Text = "";
            if (trvChildClusters.Nodes.Count != 0)  
            {
                int selectedItemIndex = Convert.ToInt32(trvChildClusters.Nodes.IndexOf(TN));
                for (int i = 0; i < newCluster[selectedItemIndex].Count; i++)
                {
                    txtResult.Text = txtResult.Text + " (" +
                        ((double[])(((csDataCluster)(newCluster[selectedItemIndex]))[i]))[0] + ", " +
                        ((double[])(((csDataCluster)(newCluster[selectedItemIndex]))[i]))[1] + ") " +
                        ((i != newCluster[selectedItemIndex].Count - 1) ? " - " : "");
                    txtResult.Text = txtResult.Text + "\r\n"; ;

                }
            }
        }
        catch (Exception ex)
        {
            bool formErr = false;
            if (!formErr)
                txtSummary.Text = "An error occured during compiling the program. The Error message is: \r\n \r\n" + ex.ToString();
        }
    }

    protected void btnCancel_Click(object sender, EventArgs e)
    {
        Response.Redirect("step3.aspx");
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        Response.Redirect("step2.aspx");
    }

    protected void btnPrevious_Click(object sender, EventArgs e)
    {
        Response.Redirect("step2.aspx");
    }
 
    void Browse()
    {
        try
        {
            int minInd = 0 , maxInd = 0 , medInd = 0;
            DateTime startTotalTime = DateTime.Now;
            csCluster selectedCluster = new csCluster();
            csKMeans kMeans = new csKMeans();
            txtResult.Text = "";
            string attr = Request.QueryString["attr"];
            string f_attr = "C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\Cluster_data_" + attr +".txt";
            initialCluster = kMeans.RetrieveData(f_attr);
            DateTime startRoutineTime = DateTime.Now;
            selectedCluster = (csCluster)(kMeans.CloneObject((csCluster)(kMeans.kMeans(initialCluster, 3))));
            DateTime endRoutineTime = DateTime.Now;
            TimeSpan routineDuration = endRoutineTime - startRoutineTime;

            double min = 0 , max = 0 , med = 0;
            selectedCluster = (csCluster)(kMeans.CloneObject((csCluster)(GetClustersSummery(selectedCluster))));

            trvChildClusters.Nodes.Clear();

            if (selectedCluster.Count > 0)
            {
                
                min = selectedCluster[0].MinLowerBound;
                max = selectedCluster[0].MaxUpperBound;
                med = selectedCluster[0].MinLowerBound;
   
                for (int i = 0; i < selectedCluster.Count; i++)
                {
                    TreeNode TN = new TreeNode();
                    trvChildClusters.Nodes.Add(TN);
                    if (min > selectedCluster[i].MinLowerBound)
                    {
                        minInd = i;
                        min = selectedCluster[i].MinLowerBound;
                    }
                
                    if (max < selectedCluster[i].MaxUpperBound)
                    {
                        maxInd = i;
                        max = selectedCluster[i].MaxUpperBound;
                    }

                }

                for (int e = 0; e < 3; e++)
                {
                    if ((e != maxInd) && (e != minInd))
                    {
                        medInd = e;
                        med = (selectedCluster[e].MinLowerBound + selectedCluster[e].MaxUpperBound) / 2;
                    }
                }
 
                double[] rangemin = new double[2];
                double[] rangemed = new double[2];
                double[] rangemed_child = new double[2];
                double[] rangemax = new double[2];

                txtClustersDetails.Text += maxInd.ToString() + medInd.ToString() + minInd.ToString() + "\n";
                rangemin = selectedCluster[minInd].Max();
                rangemed = selectedCluster[medInd].First();
                rangemax = selectedCluster[maxInd].Max();

                rangemed_child = selectedCluster[medInd][0]; 
                double min_med, max_med;
                min_med = rangemed_child[0];
                max_med = rangemed_child[1];
                for (int p = 1; p < selectedCluster[medInd].Count(); p++)
                {
                    if (selectedCluster[medInd][p].Min() < min_med)
                        min_med = selectedCluster[medInd][p].Min();
                    if (selectedCluster[medInd][p].Max() > max_med)
                        max_med = selectedCluster[medInd][p].Max();
                }

                trvChildClusters.Nodes[0].Text += "Cluster 1 (min-max range) : (" +rangemin[0] + " , " + rangemin[1] + "): poor";
                trvChildClusters.Nodes[0].Text = trvChildClusters.Nodes[minInd].Text;
                if (trvChildClusters.Nodes[0].Text == "")
                    Response.Redirect("step3.aspx?attr=reliability"); 

                trvChildClusters.Nodes[1].Text += "Cluster 2 (min-max range) : (" + min_med + " , " + max_med + "): medium";
                trvChildClusters.Nodes[1].Text = trvChildClusters.Nodes[medInd].Text;
                if (trvChildClusters.Nodes[1].Text == "")
                    Response.Redirect("step3.aspx?attr=reliability"); 

                trvChildClusters.Nodes[2].Text += "Cluster 3 (min-max range) :  (" +rangemax[0] + " , " + rangemax[1] + "): good";   
                trvChildClusters.Nodes[2].Text = trvChildClusters.Nodes[maxInd].Text;
                if (trvChildClusters.Nodes[2].Text == "")
                    Response.Redirect("step3.aspx?attr=reliability"); 

                cluster = (csCluster)(kMeans.CloneObject((csCluster)(selectedCluster)));
                
            }

            Session["cluster"] = cluster;
            DateTime stopTotalTime = DateTime.Now;
            TimeSpan tolalDuration = stopTotalTime - startTotalTime;

            txtSummary.Text = "Runtime's Durations:  \n" + "Minutes: " + cluster.RunTimeDuration.Minutes +
                              " - Seconds: " + cluster.RunTimeDuration.Seconds +
                              " - Millisecond: " + cluster.RunTimeDuration.Milliseconds + "\r\n";
            txtSummary.Text += "Total Durations:   \n" + "Minutes: " + tolalDuration.Minutes +
                " - Seconds: " + tolalDuration.Seconds + " - Millisecond: " + tolalDuration.Milliseconds + "\r\n";
        }
        catch (Exception ex)
        {
            bool formErr = false;

            if (!formErr)
                txtSummary.Text = "An error occured during compiling the program. The Error message is: \r\n \r\n" + ex.ToString();
        }
    }
}

