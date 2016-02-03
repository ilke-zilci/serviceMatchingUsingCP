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
using System.Data.Sql;
using System.Data.SqlClient;


public partial class XMLconvertorOffers : System.Web.UI.Page
{
    public XmlDocument xmlDoc = new XmlDocument();
    public XmlElement QoSConstraintsElement;  

    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        int count = 1;
        StreamReader tr = new StreamReader("C:\\Users\\delnavaz\\Desktop\\qws\\index\\" + ddlKey.SelectedItem.Text + "_input.txt");
        while (!tr.EndOfStream)
        {
            writeXML(tr.ReadLine(), count);
            count++;
        }
        tr.Close();

        StreamWriter tw = new StreamWriter("C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + ddlKey.SelectedItem.Text + "\\input.txt");
        tw.WriteLine("C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + ddlKey.SelectedItem.Text + "\\myQL2.xml");
        for (int y = 1; y < count; y++)
        {
            tw.WriteLine("C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" +
            ddlKey.SelectedItem.Text + "\\S" + y + ".xml");
        }
        tw.Close();
        Session["offers_cnt"] = count.ToString();
    }

    void writeXML(string strInputLine , int cnt)
    {
        xmlDoc.RemoveAll();
        QoSConstraintsElement = xmlDoc.CreateElement("QoSConstraints");
        foreach (ListItem li in ListBox2.Items)
        {
            write_Element(li.Text , strInputLine);
        }
        Insert_result(strInputLine);
        xmlDoc.AppendChild(QoSConstraintsElement);
        xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" +
            ddlKey.SelectedItem.Text + "\\S" + cnt + ".xml");
    }

    void Insert_result(string strLine)
    {
        string[] strl = strLine.Split(',');
        //strl[9] = strl[9].Substring(strl[9].IndexOf("title:"), strl[9].IndexOf("\""));
        //strl[strl.Count() - 1] = strl[strl.Count() - 1].Substring("address:");

        SqlConnection myConnection = new SqlConnection("server=localhost;database=test;Trusted_Connection=Yes");
        SqlCommand myCommand = new SqlCommand();
        string insertCmd;

        insertCmd = "insert into tbl_result values ( " + strl[0] + "," + strl[1] + "," + strl[2] + "," + strl[7] + ")"; // +strl[9] + ")";
        //@Sname, @responsetime, @availability, @latency, @throughput)";
        myCommand = new SqlCommand(insertCmd, myConnection);

        myCommand.Connection.Open();
        myCommand.ExecuteNonQuery();
        myCommand.Connection.Close();
    }


    void write_Element(string element_name, string strLine)
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

        string[] strl = strLine.Split(',' );

        if (element_name.Equals("reliability")) //#5
        {
            typePElement.InnerText = "range";
            fromElement.InnerText = strl[4];
            toElement.InnerText = strl[4];
            unitPElement.InnerText = "%";
            tendencyPElement.InnerText = "positive";
            weightPElement.InnerText = "0";
            relaxationPElement.InnerText = "0";
            preferencePElement.InnerText = "0";
        }

        if (element_name.Equals("response time")) //#1
        {
            typePElement.InnerText = "range";
            fromElement.InnerText = strl[0];
            toElement.InnerText = strl[0];
            unitPElement.InnerText = "msec";
            tendencyPElement.InnerText = "negative";
            weightPElement.InnerText = "0";
            relaxationPElement.InnerText = "0";
            preferencePElement.InnerText = "0";
        }

        if (element_name.Equals("availability")) //#2
        {
            typePElement.InnerText = "range";
            fromElement.InnerText = strl[1];
            toElement.InnerText = strl[1];
            unitPElement.InnerText = "%";
            tendencyPElement.InnerText = "positive";
            weightPElement.InnerText = "0";
            relaxationPElement.InnerText = "0";
            preferencePElement.InnerText = "0";
        }
        if (element_name.Equals("latency")) //#8
        {

            typePElement.InnerText = "range";
            fromElement.InnerText = strl[7];
            toElement.InnerText = strl[7];
            unitPElement.InnerText = "msec";
            tendencyPElement.InnerText = "negative";
            weightPElement.InnerText = "0";
            relaxationPElement.InnerText = "0";
            preferencePElement.InnerText = "0";
        }
        if (element_name.Equals("throughput")) //#3
        {

            typePElement.InnerText = "range";
            fromElement.InnerText = strl[2];
            toElement.InnerText = strl[2];
            unitPElement.InnerText = "%";
            tendencyPElement.InnerText = "positive";
            weightPElement.InnerText = "0";
            relaxationPElement.InnerText = "0";
            preferencePElement.InnerText = "0";
        }
        
        QoSConstraintElement.AppendChild(unitPElement);
        QoSConstraintElement.AppendChild(typePElement);
        QoSConstraintElement.AppendChild(tendencyPElement);
        QoSConstraintElement.AppendChild(preferencePElement);
        QoSConstraintElement.AppendChild(relaxationPElement);
        QoSConstraintElement.AppendChild(weightPElement);
    }

    protected void btnAddList_Click(object sender, EventArgs e)
    {
        ListBox2.Items.Add(ListBox1.SelectedItem);
    }
    protected void btnRemoveList_Click(object sender, EventArgs e)
    {
        ListBox2.Items.Remove(ListBox1.SelectedItem);
    }
}
