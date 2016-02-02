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
using System.Xml;
using System.IO;

public partial class step2 : System.Web.UI.Page
{
    public csCluster cluster = new csCluster();
    public csCluster initialCluster = new csCluster();
    public XmlDocument xmlDoc = new XmlDocument();
    public XmlElement QoSConstraintsElement;
    public string Qname2 = "";
    public double[] result = new double[2];

    protected void Page_Load(object sender, EventArgs e)
    {
        string attrList = Session["attrList"].ToString();
        Qname2 = Session["keyword"].ToString();

      
        string[] attr = new string[10];
        attr = attrList.Split('*');
        foreach (string si in attr)
        {
            //TextBox1.Text += si;
            if (si.Equals("price"))
                chPrice.Visible = true;
            if (si.Equals("response time"))
                chResponseTime.Visible = true;
            if (si.Equals("reliability"))
                chReliability.Visible = true;
            if (si.Equals("authentication"))
                chAuthentication.Visible = true;
            if (si.Equals("compliance"))
                chCompliance.Visible = true;
            if (si.Equals("best practices"))
                chBestPractices.Visible = true;
            if (si.Equals("latency"))
                chLatency.Visible = true;
            if (si.Equals("documentation"))
                chDocumentation.Visible = true;
            if (si.Equals("successability"))
                chSuccessability.Visible = true;
            if (si.Equals("throughput"))
                chThroughput.Visible = true;
            if (si.Equals("availability"))
                chAvailability.Visible = true;

        }

    }
    protected void chPrice_CheckedChanged(object sender, EventArgs e)
    {
        if (chPrice.Checked)
        {
            PricePanel.Visible = true;
            RBPRange.Visible = true;
            RBPFuzzy.Visible = true;
        }
        else
        {
            PricePanel.Visible = false;
            RBPRange.Visible = false;
            RBPFuzzy.Visible = false;
        }
    }
    protected void RBPRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBPRange.Checked)
        {
            txtRangeFromP.Visible = true;
            txtRangeToP.Visible = true;
            RBFuzzyP.Visible = false;
            PRangePrice.Visible = true;
            //lblfromP.Visible = true;
            //lbltoP.Visible = true;
        }
    }
    protected void RBPFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBPFuzzy.Checked)
        {
            txtRangeFromP.Visible = false;
            txtRangeToP.Visible = false;
            RBFuzzyP.Visible = true;
            PRangePrice.Visible = false;
            //lblfromP.Visible = false;
            //lbltoP.Visible = false;
        }
    }
    protected void chReliability_CheckedChanged(object sender, EventArgs e)
    {
        if (chReliability.Checked)
        {
            RPanel.Visible = true;
            RBRRange.Visible = true;
            RBRFuzzy.Visible = true;
        }
        else
        {
            RPanel.Visible = false;
            RBRRange.Visible = false;
            RBRFuzzy.Visible = false;
        }
    }
    protected void chResponseTime_CheckedChanged(object sender, EventArgs e)
    {
        if (chResponseTime.Checked)
        {
            RTPanel.Visible = true;
            RBRTRange.Visible = true;
            RBRTFuzzy.Visible = true;
        }
        else
        {
            RTPanel.Visible = false;
            RBRTRange.Visible = false;
            RBRTFuzzy.Visible = false;
        }
    }

    protected void RBRTRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRTRange.Checked)
        {
            txtRangeFromRT.Visible = true;
            txtRangeToRT.Visible = true;
            RBFuzzyRT.Visible = false;
            PRangeRT.Visible = true;
            //lblfromRT.Visible = true;
            //lbltoRT.Visible = true;

        }

    }
    protected void RBRTFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRTFuzzy.Checked)
        {
            txtRangeFromRT.Visible = false;
            txtRangeToRT.Visible = false;
            RBFuzzyRT.Visible = true;
            PRangeRT.Visible = false;
            //lblfromRT.Visible = false;
            //lbltoRT.Visible = false;

        }
    }
    protected void RBRRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRRange.Checked)
        {
            txtRangeFromR.Visible = true;
            txtRangeToR.Visible = true;
            RBFuzzyR.Visible = false;
            PRangeR.Visible = true;
            //lblfromR.Visible = true;
            //lbltoR.Visible = true;

        }
    }

    protected void RBRFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRFuzzy.Checked)
        {
            txtRangeFromR.Visible = false;
            txtRangeToR.Visible = false;
            RBFuzzyR.Visible = true;
            PRangeR.Visible = false;
            //lblfromR.Visible = false;
            //lbltoR.Visible = false;
        }
    }

     protected void chAuthentication_CheckedChanged(object sender, EventArgs e)
    {
        if (chAuthentication.Checked)
        {
            RBAsingle.Visible = true;
            ddlSingleAuth.Visible = true;
            AuthPanel.Visible = true;
        }
        else
        {
            RBAsingle.Visible = false;
            ddlSingleAuth.Visible = false;
            AuthPanel.Visible = false;
        }
    }
    protected void btnCancel_Click(object sender, EventArgs e)
    {
        Response.Redirect("step2.aspx");
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        //string Req_constraints = "";
        writeXML();
        //Session["Req_constraints"] = Req_constraints;
        Response.Redirect("step4.aspx");
    }
    protected void btnBrowse_Click(object sender, EventArgs e)
    {
        string attr = "";
        if(RBPFuzzy.Visible)
            attr = "price";
        if (RBRFuzzy.Visible)
            attr = "reliability";
        if (RBRTFuzzy.Visible)
            attr = "responsetime";
        Response.Redirect("step3.aspx?attr=" + attr);

    }


    protected void btnPrevious_Click(object sender, EventArgs e)
    {
        Response.Redirect("step1.aspx");
 
    }

    void writeXML()
    {
        string rangeFromP = txtRangeFromP.Text;
        //XmlDocument xmlDoc = new XmlDocument();
        QoSConstraintsElement = xmlDoc.CreateElement("QoSConstraints");
        
        if (chPrice.Checked)
        {
            write_Element("price");
        }
        if (chReliability.Checked)
        {
            write_Element("reliability");
        }
        if (chResponseTime.Checked)
        {
            write_Element("response time");
        }
        if (chAuthentication.Checked)
        {
            write_Element("authentication");
        }
        if (chAvailability.Checked)
        {
            write_Element("availability");
        }
        if (chLatency.Checked)
        {
            write_Element("latency");
        }
        if (chThroughput.Checked)
        {
            write_Element("throughput");
        }
        /************************************************************************************************/
        xmlDoc.AppendChild(QoSConstraintsElement);
        
        xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname2 + "\\myQL2.xml");
    }

    void write_Element(string element_name)
    {
        XmlElement QoSConstraintElement = xmlDoc.CreateElement("QoSConstraint");
        QoSConstraintsElement.AppendChild(QoSConstraintElement);
        XmlElement priceNameElement = xmlDoc.CreateElement("name");
        QoSConstraintElement.AppendChild(priceNameElement);
        priceNameElement.InnerText = element_name; // "price";

        XmlElement valueElement = xmlDoc.CreateElement("values");
        QoSConstraintElement.AppendChild(valueElement);
        XmlElement fromElement = xmlDoc.CreateElement("from");
        XmlElement toElement = xmlDoc.CreateElement("to");
        valueElement.AppendChild(fromElement);
        valueElement.AppendChild(toElement);
        XmlElement typePElement = xmlDoc.CreateElement("type");
        XmlElement tendencyPElement = xmlDoc.CreateElement("tendency");
        XmlElement preferencePElement = xmlDoc.CreateElement("preference");
        XmlElement relaxationPElement = xmlDoc.CreateElement("relaxation");
        XmlElement unitPElement = xmlDoc.CreateElement("unit");
        XmlElement weightPElement = xmlDoc.CreateElement("weight");

        if (element_name.Equals("price"))
        {
            if (RBPRange.Checked)
            {
                fromElement.InnerText = txtRangeFromP.Text;
                toElement.InnerText = txtRangeToP.Text;
                typePElement.InnerText = "range";
            }
            if (RBPFuzzy.Checked)
            {
                fromElement.InnerText = RBFuzzyP.SelectedItem.Text;
                toElement.InnerText = RBFuzzyP.SelectedItem.Text;
                typePElement.InnerText = "fuzzy";
                //Session["fuzzyP"] = 

            }
            unitPElement.InnerText = ddlP.SelectedItem.Text;
            tendencyPElement.InnerText = "negative";
        }
        if (element_name.Equals("reliability"))
        {

            if (RBRRange.Checked) 
            {
                typePElement.InnerText = "range";
                fromElement.InnerText = txtRangeFromR.Text;
                toElement.InnerText = txtRangeToR.Text;
            }
            if (RBRFuzzy.Checked) 
            {
                /*if (RBFuzzyR.Items[0].Selected) //best available
                    Browse(0);*/
                if (RBFuzzyR.Items[1].Selected) //good
                    Browse(0, "reliability");
                if (RBFuzzyR.Items[2].Selected) //medium
                    Browse(1, "reliability");
                if (RBFuzzyR.Items[3].Selected) //poor
                    Browse(2, "reliability");

                typePElement.InnerText = "fuzzy";
                fromElement.InnerText = result[0].ToString(); //RBFuzzyR.SelectedItem.Text;
                toElement.InnerText = result[1].ToString(); //RBFuzzyR.SelectedItem.Text;
            }
            unitPElement.InnerText = ddlR.SelectedItem.Text;
            tendencyPElement.InnerText = "positive";
        }

        if (element_name.Equals("response time"))
        {

            if (RBRTRange.Checked)
            {
                typePElement.InnerText = "range";
                fromElement.InnerText = txtRangeFromRT.Text;
                toElement.InnerText = txtRangeToRT.Text;
            }
            if (RBRTFuzzy.Checked)
            {
                /*if (RBFuzzyR.Items[0].Selected) //best available
                    Browse(0);*/
                if (RBFuzzyRT.Items[1].Selected) //good
                    Browse(0, "response time");
                if (RBFuzzyRT.Items[2].Selected) //medium
                    Browse(1, "response time");
                if (RBFuzzyRT.Items[3].Selected) //poor
                    Browse(2, "response time");

                typePElement.InnerText = "fuzzy";
                fromElement.InnerText = result[0].ToString(); 
                toElement.InnerText = result[1].ToString(); 
            }
            unitPElement.InnerText = ddlRT.SelectedItem.Text;
            tendencyPElement.InnerText = "negative";
        }
        if (element_name.Equals("authentication"))
        {
            if (RBAsingle.Checked)
            {
                typePElement.InnerText = "single";
                if (ddlSingleAuth.SelectedItem.Text == "yes")
                {
                    fromElement.InnerText = "true";
                    toElement.InnerText = "true";
                }
                else
                {
                    fromElement.InnerText = "false";
                    toElement.InnerText = "false";
                }
            }
            unitPElement.InnerText = "boolean";
            tendencyPElement.InnerText = "neutral";
        }
        if (element_name.Equals("availability"))
        {
            if (RBRangeA.Checked)
            {
                typePElement.InnerText = "range";
                fromElement.InnerText = txtRangeFromAv.Text;
                toElement.InnerText = txtRangeToAv.Text;
            }
            if (RBFuzzyA.Checked)
            {
                /*if (RBFuzzyR.Items[0].Selected) //best available
                    Browse(0);*/
                if (RBFuzzyAvailability.Items[1].Selected) //good
                    Browse(0, "availability");
                if (RBFuzzyAvailability.Items[2].Selected) //medium
                    Browse(1, "availability");
                if (RBFuzzyAvailability.Items[3].Selected) //poor
                    Browse(2, "availability");

                typePElement.InnerText = "fuzzy";
                fromElement.InnerText = result[0].ToString();
                toElement.InnerText = result[1].ToString(); 
            }
            unitPElement.InnerText = ddlAv.SelectedItem.Text;
            tendencyPElement.InnerText = "positive";
        }
        if (element_name.Equals("latency"))
        {

            if (RBLatencyRange.Checked)
            {
                typePElement.InnerText = "range";
                fromElement.InnerText = txtRangeFromL.Text;
                toElement.InnerText = txtRangeToL.Text;
            }
            if (RBLatencyFuzzy.Checked)
            {
                /*if (RBFuzzyR.Items[0].Selected) //best available
                    Browse(0);*/
                if (RBFuzzyL.Items[1].Selected) //good
                    Browse(0, "latency");
                if (RBFuzzyL.Items[2].Selected) //medium
                    Browse(1, "latency");
                if (RBFuzzyL.Items[3].Selected) //poor
                    Browse(2, "latency");

                typePElement.InnerText = "fuzzy";
                fromElement.InnerText = result[0].ToString();
                toElement.InnerText = result[1].ToString(); 
            }
            unitPElement.InnerText = ddlLt.SelectedItem.Text;
            tendencyPElement.InnerText = "negative";
        }
        if (element_name.Equals("throughput"))
        {

            if (RBThroughputRange.Checked)
            {
                typePElement.InnerText = "range";
                fromElement.InnerText = txtRangeFromTh.Text;
                toElement.InnerText = txtRangeToTh.Text;
            }
            if (RBThrouhputFuzzy.Checked)
            {
                /*if (RBFuzzyR.Items[0].Selected) //best available
                    Browse(0);*/
                if (PFuzzyThroughput.Items[1].Selected) //good
                    Browse(0, "throughput");
                if (PFuzzyThroughput.Items[2].Selected) //medium
                    Browse(1, "throughput");
                if (PFuzzyThroughput.Items[3].Selected) //poor
                    Browse(2, "throughput");

                typePElement.InnerText = "fuzzy";
                fromElement.InnerText = result[0].ToString();
                toElement.InnerText = result[1].ToString(); 
            }
            unitPElement.InnerText = ddlTh.SelectedItem.Text;
            tendencyPElement.InnerText = "positive";
        }  

        QoSConstraintElement.AppendChild(unitPElement);
        QoSConstraintElement.AppendChild(weightPElement);
        QoSConstraintElement.AppendChild(typePElement);
        QoSConstraintElement.AppendChild(tendencyPElement);
        QoSConstraintElement.AppendChild(preferencePElement);
        QoSConstraintElement.AppendChild(relaxationPElement);

    }

    protected void chLatency_CheckedChanged(object sender, EventArgs e)
    {
        if (chLatency.Checked)
        {
            LatencyPanel.Visible = true;
            RBLatencyRange.Visible = true;
            RBLatencyFuzzy.Visible = true;
        }
        else
        {
            LatencyPanel.Visible = false;
            RBLatencyRange.Visible = false;
            RBLatencyFuzzy.Visible = false;
        }
    }
    protected void RBLatencyRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBLatencyRange.Checked)
        {
            PRangeL.Visible = true;
            RBFuzzyL.Visible = false;
        }
    }
    protected void RBAvailabilityRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRangeA.Checked)
        {
            PRangeAvailability.Visible = true;
            RBFuzzyAvailability.Visible = false;
        }
    }
    protected void RBAvailabilityFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRangeA.Checked)
        {
            PRangeAvailability.Visible = false;
            RBFuzzyAvailability.Visible = true;
        }
    }
    protected void RBLatencyFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBLatencyFuzzy.Checked)
        {
            PRangeL.Visible = false;
            RBFuzzyL.Visible = true;
        }

    }
    protected void RBDocRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBDocRange.Checked)
        {
            PRangeDoc.Visible = true;
            RBFuzzyDoc.Visible = false;
        }
    }
    protected void RBDocFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBDocFuzzy.Checked)
        {
            PRangeDoc.Visible = false;
            RBFuzzyDoc.Visible = true;
        }
    }
    protected void chDocumentation_CheckedChanged(object sender, EventArgs e)
    {
        if (chDocumentation.Checked)
        {
            DocumentationPanel.Visible = true;
            RBDocRange.Visible = true;
            RBDocFuzzy.Visible = true;
        }
        else
        {
            DocumentationPanel.Visible = false;
            RBDocRange.Visible = false;
            RBDocFuzzy.Visible = false;
        }
    }
    protected void chBestPractices_CheckedChanged(object sender, EventArgs e)
    {
        if (chBestPractices.Checked)
        {
            BPPanel.Visible = true;
            RBBPRange.Visible = true;
            RBBPFuzzy.Visible = true;
        }
        else
        {
            BPPanel.Visible = false;
            RBBPRange.Visible = false;
            RBBPFuzzy.Visible = false;
        }
    }
    protected void RBBPRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBBPRange.Checked)
        {
            PRangeBP.Visible = true;
            RBFuzzyBP.Visible = false;
        }

    }
    protected void RBBPFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBBPFuzzy.Checked)
        {
            PRangeBP.Visible = false;
            RBFuzzyBP.Visible = true;
        }
    }
    protected void chAvailability_CheckedChanged(object sender, EventArgs e)
    {
        if (chAvailability.Checked)
        {
            AvailabilityPanel.Visible = true;
            RBRangeA.Visible = true;
            RBFuzzyA.Visible = true;
        }
        else
        {
            AvailabilityPanel.Visible = false;
            RBRangeA.Visible = false;
            RBFuzzyA.Visible = false;
        }
    }
    protected void chThroughput_CheckedChanged(object sender, EventArgs e)
    {
        if (chThroughput.Checked)
        {
            ThroughputPanel.Visible = true;
            RBThroughputRange.Visible = true;
            RBThrouhputFuzzy.Visible = true;
        }
        else
        {
            ThroughputPanel.Visible = false;
            RBThroughputRange.Visible = false;
            RBThrouhputFuzzy.Visible = false;
        }
    }
    protected void RBThrouhputFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBThrouhputFuzzy.Checked)
        {
            PRangeThroughput.Visible = false;
            PFuzzyThroughput.Visible = true;
        }
    }
    protected void RBThroughputRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBThroughputRange.Checked)
        {
            PRangeThroughput.Visible = true;
            PFuzzyThroughput.Visible = false;
        }
    }

    void Browse(int cond, string attribute)
    {
            int minInd = 0, maxInd = 0, medInd = 0;
            DateTime startTotalTime = DateTime.Now;
            csCluster selectedCluster = new csCluster();
            csKMeans kMeans = new csKMeans();
            //txtResult.Text = "";
            //string attr = Request.QueryString["attr"];
            string f_attr = "C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\Cluster_data_" + attribute + ".txt";
            initialCluster = kMeans.RetrieveData(f_attr);
            DateTime startRoutineTime = DateTime.Now;
            selectedCluster = (csCluster)(kMeans.CloneObject((csCluster)(kMeans.kMeans(initialCluster, 3))));
            DateTime endRoutineTime = DateTime.Now;
            TimeSpan routineDuration = endRoutineTime - startRoutineTime;

            double min = 0, max = 0, med = 0;
            selectedCluster = (csCluster)(kMeans.CloneObject((csCluster)(GetClustersSummery(selectedCluster))));

            if (selectedCluster.Count > 0)
            {

                min = selectedCluster[0].MinLowerBound;
                max = selectedCluster[0].MaxUpperBound;
                med = selectedCluster[0].MinLowerBound;

                for (int i = 0; i < selectedCluster.Count; i++)
                {
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
                if (cond == 2)
                {
                    result[0] = rangemin[0];
                    result[1] = rangemin[1];
                }
                if (cond == 1)
                {
                    result[0] = min_med;
                    result[1] = max_med;
                }
                if (cond == 0)
                {
                    result[0] = rangemax[0];
                    result[1] = rangemax[1];
                }
                cluster = (csCluster)(kMeans.CloneObject((csCluster)(selectedCluster)));

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
}
