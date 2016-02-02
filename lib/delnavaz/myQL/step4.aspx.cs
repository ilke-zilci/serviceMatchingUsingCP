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


public partial class step4 : System.Web.UI.Page
{
    public XmlDocument xmlDoc = new XmlDocument();
    public static int offers_cnt = 6;
    public string Qname4 = "";

    protected void Page_Load(object sender, EventArgs e)
    {
       //string attrList = "*response time*availability*latency*throughput";
       string attrList = Session["attrList"].ToString();

       Qname4 = Session["keyword"].ToString(); //"commerce"; 
       //TextBox1.Text = attrList;
        
        string[] attr = new string[10];
        attr = attrList.Split('*');
        foreach (string si in attr)
        {
            //TextBox1.Text += si;
            if (si.Equals("price"))
            {
                chPricePref.Visible = true;
                chPrice1.Visible = true;
                txtPricePref.Visible = true;
                txtPriceRelax.Visible = true;
            }
            if (si.Equals("response time"))
            {
                chResponseTimePref.Visible = true;
                chResponseTime1.Visible = true;
                txtResponseTimePref.Visible = true;
                txtResponseTimeRelax.Visible = true;
            }
            if (si.Equals("reliability"))
            {
                chReliabilityPref.Visible = true;
                chReliability1.Visible = true;
                txtReliabilityPref.Visible = true;
                txtReliabilityRelax.Visible = true;
            }
            if (si.Equals("authentication"))
            {
                chAuthPref.Visible = true;
                chAuth.Visible = true;
                txtAuthPref.Visible = true;
                txtAuthRelax.Visible = true;
            }
            if (si.Equals("availability"))
            {
                chAvPRef.Visible = true;
                chAv.Visible = true;
                txtAvPref.Visible = true;
                txtAvRelax.Visible = true;
            }
            if (si.Equals("latency"))
            {
                chLatency.Visible = true;
                chLatencyPref.Visible = true;
                txtLatencyPref.Visible = true;
                txtLatencyRelax.Visible = true;
            }
            if (si.Equals("throughput"))
            {
                chThroughput.Visible = true;
                chThroughputPref.Visible = true;
                txtThroughputPref.Visible = true;
                txtThroughputRelax.Visible = true;
            }
        }
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname4 + "\\myQL2.xml"); //xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\testCommerce\\myQL2.xml");
        xmlDoc = writeXML_Pref(xmlDoc);
        xmlDoc = writeXML_Relax(xmlDoc);
        xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname4 + "\\myQL2.xml");


        /*xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname + "\\myQL2.xml"); //(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\testCommerce\\myQL2.xml");
        xmlDoc = writeXML_Relax(xmlDoc);
        xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname + "\\myQL2.xml");*/

        for (int y = 1; y <= offers_cnt; y++)
        {
            xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname4 + "\\S" + y + ".xml");
            xmlDoc = writeXML_Pref(xmlDoc);
            xmlDoc = writeXML_Relax(xmlDoc);
            xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname4 + "\\S" + y + ".xml");
        }

        /*for (int y = 1; y <= offers_cnt; y++)
        {
            xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname + "\\S" + y + ".xml");
            xmlDoc = writeXML_Relax(xmlDoc);
            xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname + "\\S" + y + ".xml");
        }*/

         Response.Redirect("step5.aspx");
    }
    protected void btnPrevious_Click(object sender, EventArgs e)
    {
        Response.Redirect("step2.aspx");
    }
    protected void btnCancel_Click(object sender, EventArgs e)
    {
        Response.Redirect("step4.aspx");
    }


    XmlDocument writeXML_Pref(XmlDocument xmlDoc)
    {
        XmlNode node;
        node = xmlDoc.DocumentElement;

        if (chPricePref.Checked)
        {
            foreach(XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "price")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtPricePref.Text;
                        node1.AppendChild(newNode);
                    }
        }
        if (chReliabilityPref.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "reliability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtReliabilityPref.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("reliability");
        }
        if (chResponseTimePref.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "response time")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtResponseTimePref.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("responsetime");
        }
        if (chAuthPref.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "authentication")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtAuthPref.Text;
                        node1.AppendChild(newNode); 
                    }
            //update_Element("authentication");
        }
        if (chAvPRef.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "availability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtAvPref.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("availability");
        }
        if (chLatencyPref.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "latency")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtLatencyPref.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("latency");
        }
        if (chThroughputPref.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "throughput")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("preference"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "preference", "");
                        newNode.InnerText = txtThroughputPref.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("throughput");
        }
        /************************************************************************************************/
        
        return xmlDoc;
    }

    XmlDocument writeXML_Relax(XmlDocument xmlDoc)
    {
        XmlNode node;
        node = xmlDoc.DocumentElement;

        if (chPrice1.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "price")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtPriceRelax.Text;
                        node1.AppendChild(newNode);
                    }
        }
        if (chReliability1.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "reliability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtReliabilityRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("reliability");
        }
        if (chResponseTime1.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "response time")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtResponseTimeRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("responsetime");
        }
        if (chAuth.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "authentication")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtAuthRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("authentication");
        }
        if (chAv.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "availability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtAvRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("authentication");
        }
        if (chLatency.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "latency")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtLatencyRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("authentication");
        }
        if (chThroughput.Checked)
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "throughput")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("relaxation"))
                                node1.RemoveChild(node3);
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "relaxation", "");
                        newNode.InnerText = txtThroughputRelax.Text;
                        node1.AppendChild(newNode);
                    }
            //update_Element("authentication");
        }
        /************************************************************************************************/
        return xmlDoc;
    }

    protected void chPreferences_CheckedChanged(object sender, EventArgs e)
    {
        if (chPreferences.Checked)
        {
            chPricePref.Checked = true;
            chAvPRef.Checked = true;
            chAuthPref.Checked = true;
            chLatencyPref.Checked = true;
            chThroughputPref.Checked = true;
            chReliabilityPref.Checked = true;
            chResponseTimePref.Checked = true;

        }
    }
    protected void chRelaxationSet_CheckedChanged(object sender, EventArgs e)
    {
        if (chRelaxationSet.Checked)
        {
            chPrice1.Checked = true;
            chAv.Checked = true;
            chAuth.Checked = true;
            chLatency.Checked = true;
            chThroughput.Checked = true;
            chReliability1.Checked = true;
            chResponseTime1.Checked = true;

        }
    }
}
