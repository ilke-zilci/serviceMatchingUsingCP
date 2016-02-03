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

public partial class Interface1 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void chAvailability_CheckedChanged(object sender, EventArgs e)
    {
        RBA.Enabled = true;
    }
    protected void RBA_SelectedIndexChanged(object sender, EventArgs e)
    {
        if(RBA.Items[0].Selected)
        {//single value
         txtSingleA.Visible = true;
         txtRangeFromA.Visible = false;
         txtRangeToA.Visible = false;
         RBFuzzyA.Visible = false;
         lblfrom1.Visible = false;
         lblto1.Visible = false;
         //btnBrowsA.Visible = false;
         linkBrowseA.Visible = false;
        }

        if(RBA.Items[1].Selected)
        { //range value
          txtSingleA.Visible = false;
          txtRangeFromA.Visible = true;
          txtRangeToA.Visible = true;
          RBFuzzyA.Visible = false;
          lblfrom1.Visible = true;
          lblto1.Visible = true;
          //btnBrowsA.Visible = true;
          linkBrowseA.Visible = true;
        }

        if(RBA.Items[2].Selected)
        { //fuzzy value
          txtSingleA.Visible = false;
          txtRangeFromA.Visible = false;
          txtRangeToA.Visible = false;
          RBFuzzyA.Visible = true;
          lblfrom1.Visible = false;
          lblto1.Visible = false;
          //btnBrowsA.Visible = false;
          linkBrowseA.Visible = false;
        }

    }
    protected void chPrice_CheckedChanged(object sender, EventArgs e)
    {
        RBP.Enabled = true;
    }
    protected void RBP_SelectedIndexChanged(object sender, EventArgs e)
    {
        if (RBP.Items[0].Selected)
        {//single value
            txtSingleP.Visible = true;
            txtRangeFromP.Visible = false;
            txtRangeToP.Visible = false;
            RBFuzzyP.Visible = false;
        }

        if (RBP.Items[1].Selected)
        { //range value
            txtSingleP.Visible = false;
            txtRangeFromP.Visible = true;
            txtRangeToP.Visible = true;
            RBFuzzyP.Visible = false;
        }

        if (RBP.Items[2].Selected)
        { //fuzzy value
            txtSingleP.Visible = false;
            txtRangeFromP.Visible = false;
            txtRangeToP.Visible = false;
            RBFuzzyP.Visible = true;
        }
    }
    protected void btnMIP_Click(object sender, EventArgs e)  // fetching first parsed XML offer file and craete the MIP problem
    {
        //read from offer file , we assume the offer has a same xml file as the query so we just send the xml offer file to the parseXML() 
        string strOffer = "";
        strOffer = parseXML("C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myOffer.xml");
        txttest.Text += "offer:" + strOffer;
        //get the string from parseXML() 
        string strQuery = "";
        strQuery = parseXML("C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL2.xml");
        txttest.Text += "query:" + strQuery;
        //write a MIPP with offer file and string parser
        string strO1 = "", strO2 = "", strO3 = "", strO4 = "";
        string strQ1 = "", strQ2 = "", strQ3 = "", strQ4 = "";
        strO1 = strOffer.Substring(0, strOffer.IndexOf(";"));
        strOffer = strOffer.Substring(strO1.Length + 1);
        strO2 = strOffer.Substring(0, strOffer.IndexOf(";"));
        strOffer = strOffer.Substring(strO2.Length + 1);
        strO3 = strOffer.Substring(0, strOffer.IndexOf(";"));
        strOffer = strOffer.Substring(strO3.Length + 1);
        strO4 = strOffer.Substring(0, strOffer.IndexOf(";"));

        strQ1 = strQuery.Substring(0, strQuery.IndexOf(";"));
        strQuery = strQuery.Substring(strQ1.Length + 1);
        strQ2 = strQuery.Substring(0, strQuery.IndexOf(";"));
        strQuery = strQuery.Substring(strQ2.Length + 1);
        strQ3 = strQuery.Substring(0, strQuery.IndexOf(";"));
        strQuery = strQuery.Substring(strQ3.Length + 1);
        strQ4 = strQuery.Substring(0, strQuery.IndexOf(";"));

        //txtMIP.Text = strO1 + "**" + strO2 + "**" + strO3 + "**" + strO4;
        //txtMIP.Text += strQ1 + "**" + strQ2 + "**" + strQ3 + "**" + strQ4;
        string strMIPP = "min : ";
        int op1 =0 , op2 =0;
        int x1_1 = 0, x1_2 = 0, x2_1 = 0, x2_2 = 0;
        //int res = 0;
        //strMIPP += strO1.Substring(strO1.IndexOf(">") + 1 );
        op1 = Convert.ToInt32(strO1.Substring(strO1.IndexOf(">") + 1 ));
        //strMIPP += "  x1 - ";
        //strMIPP += strQ1.Substring(strQ1.IndexOf(">") + 1 );
        op2 = Convert.ToInt32(strQ1.Substring(strQ1.IndexOf(">") + 1 ));
        //strMIPP += "  x1 ";
        //strMIPP += Math.Abs(op1 - op2).ToString() + "x1";
        x1_1 = Math.Abs(op1 - op2);
        //strMIPP += " + ";
        op1 = Convert.ToInt32(strO2.Substring(strO2.IndexOf("<") + 1)); //strMIPP += strO2.Substring(strO2.IndexOf("<") + 1);
        //strMIPP += "  x2 - ";
        op2 = Convert.ToInt32(strQ2.Substring(strQ2.IndexOf("<") + 1)); //strMIPP += strQ2.Substring(strQ2.IndexOf("<") + 1);
        //strMIPP += "  x2 ";
        //strMIPP += Math.Abs(op1 - op2).ToString() + "x2";
        x2_1 = Math.Abs(op1 - op2);
        //strMIPP += " + ";

        //strMIPP += strO3.Substring(strO3.IndexOf(">") + 1);
        op1 = Convert.ToInt32(strO3.Substring(strO3.IndexOf(">") + 1));
        //strMIPP += " x1 - ";
        //strMIPP += strQ3.Substring(strQ3.IndexOf(">") + 1);
        op2 = Convert.ToInt32(strQ3.Substring(strQ3.IndexOf(">") + 1));
        //strMIPP += " x1 ";
        //strMIPP += Math.Abs(op1 - op2).ToString() + "x1";
        x1_2 = Math.Abs(op1 - op2); 
        //strMIPP += " + ";
        //strMIPP += strO4.Substring(strO4.IndexOf("<") + 1);
        op1 = Convert.ToInt32(strO4.Substring(strO4.IndexOf("<") + 1));
        //strMIPP += " x2 - ";
        //strMIPP += strQ4.Substring(strQ4.IndexOf("<") + 1);
        op2 = Convert.ToInt32(strQ4.Substring(strQ4.IndexOf("<") + 1));
       // strMIPP += " x2 ";
        //strMIPP += Math.Abs(op1 - op2).ToString() + "x2";
        x2_2 = Math.Abs(op1 - op2);
        //strMIPP += " ; ";
        
        strMIPP = (x1_1 + x1_2).ToString() + "x1" + (x2_1 + x2_2).ToString() + "x2";

        txtMIP.Text = strMIPP;
        // write a lp_solve file so we can open with the lp_solve IDE
        System.IO.StreamWriter file = new System.IO.StreamWriter("C:\\Users\\delnavaz\\Desktop\\lpsolver-problem\\test.lp");
        file.WriteLine(strMIPP);
        file.WriteLine(parseXML("C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myOffer.xml"));
        file.WriteLine(parseXML("C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL2.xml"));
        file.Close();


    }
    protected void btnXML_Click(object sender, EventArgs e) // writing and XML query based on this request
    {
       writeXML();
    }
    protected void btnXMLParse_Click(object sender, EventArgs e) // parsing the XML query to write the constraints for MIP problem
    {
        string strResult = "";
        strResult = parseXML("C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL2.xml");
        txttest.Text += "query:" + strResult;
    }

    void writeXML()
    {
        string rangeFromP = txtRangeFromP.Text;
        XmlDocument xmlDoc = new XmlDocument();

        XmlElement QueryElement = xmlDoc.CreateElement("Query");
        XmlElement constraintsElement = xmlDoc.CreateElement("constraints");
        QueryElement.AppendChild(constraintsElement);

        XmlElement priceElement = xmlDoc.CreateElement("price");
        constraintsElement.AppendChild(priceElement);

        XmlElement fromElement = xmlDoc.CreateElement("from");
        XmlElement toElement = xmlDoc.CreateElement("to");
        if (RBP.SelectedIndex == 0) //single value
        {
            fromElement.InnerText = txtSingleP.Text;
            toElement.InnerText = txtSingleP.Text;
        }
        if (RBP.SelectedIndex == 1) //range value
        {
            fromElement.InnerText = txtRangeFromP.Text;
            toElement.InnerText = txtRangeToP.Text;
        }
        if (RBP.SelectedIndex == 2) //fuzzy value
        { // call fuzzy clustering
            fromElement.InnerText = RBFuzzyP.SelectedItem.Text;
            toElement.InnerText = RBFuzzyP.SelectedItem.Text;
        }
        priceElement.AppendChild(fromElement);
        priceElement.AppendChild(toElement);

        XmlElement unitPElement = xmlDoc.CreateElement("unit");
        unitPElement.InnerText = "$";
        priceElement.AppendChild(unitPElement);

        XmlElement valueTypePElement = xmlDoc.CreateElement("valueType");
        valueTypePElement.InnerText = RBP.SelectedItem.Text; //range
        priceElement.AppendChild(valueTypePElement);

       /********************************************************************************/
        XmlElement availablityElement = xmlDoc.CreateElement("availablity");
        constraintsElement.AppendChild(availablityElement);

        XmlElement fromAElement = xmlDoc.CreateElement("from");
        XmlElement toAElement = xmlDoc.CreateElement("to");
        if (RBA.SelectedIndex == 0) //single value
        {
            fromAElement.InnerText = txtSingleA.Text;
            toAElement.InnerText = txtSingleA.Text;
        }
        if (RBA.SelectedIndex == 1) //range value
        {
            fromAElement.InnerText = txtRangeFromA.Text;
            toAElement.InnerText = txtRangeToA.Text;
        }
        if (RBA.SelectedIndex == 2) //fuzzy value
        { // call fuzzy clustering
            fromAElement.InnerText = RBFuzzyA.SelectedItem.Text;
            toAElement.InnerText = RBFuzzyA.SelectedItem.Text;
        }
        availablityElement.AppendChild(fromAElement);
        availablityElement.AppendChild(toAElement);

        XmlElement unitAElement = xmlDoc.CreateElement("unit");
        unitAElement.InnerText = "%";
        availablityElement.AppendChild(unitAElement);

        XmlElement valueTypeAElement = xmlDoc.CreateElement("valueType"); //fuzzy
        valueTypeAElement.InnerText = RBA.SelectedItem.Text;
        availablityElement.AppendChild(valueTypeAElement);

        /************************************************************************************************/
        xmlDoc.AppendChild(QueryElement);
        xmlDoc.Save(@"C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL2.xml");
    }

    string parseXML(string docAddr)  // write constraints to a file
    {
        string strRet = "";
        XmlDocument doc = new XmlDocument();
        //doc.Load(@"C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL2.xml");
        doc.Load(@docAddr);

        XmlNodeList PriceList = doc.GetElementsByTagName("price");
        foreach (XmlNode node in PriceList)
        {
            XmlElement PriceElement = (XmlElement)node;

            string from = PriceElement.GetElementsByTagName("from")[0].InnerText;
            string to = PriceElement.GetElementsByTagName("to")[0].InnerText;
          
            /*if (bookElement.HasAttributes)
            {
                isbn = bookElement.Attributes["ISBN"].InnerText;
            }*/

            strRet += "x1 > " + from + "; x1 < " + to + ";\n";
        }
        XmlNodeList AvailabilityList = doc.GetElementsByTagName("availablity");
        foreach (XmlNode node in AvailabilityList)
        {
            XmlElement AvailabilityElement = (XmlElement)node;

            string from = AvailabilityElement.GetElementsByTagName("from")[0].InnerText;
            string to = AvailabilityElement.GetElementsByTagName("to")[0].InnerText;
            strRet += "x2 > " + from + "; x2 < " + to + ";\n";
        }
        //txttest.Text = strRet;
        return strRet;
    }
    protected void btn_lp_solve_Click(object sender, EventArgs e)
    {
        int lp;
        double[] Row = new double[2];
        int[] Col = new int[2];
        Row[0] = 3.0; //=x1
        Col[0] = 1;
        Row[1] = 5.0; //=x2
        Col[1] = 2;

        lp = lpsolve55.lpsolve.make_lp(0, 2);
        lpsolve55.lpsolve.set_col_name(lp, 1, "x1");
        lpsolve55.lpsolve.set_col_name(lp, 2, "x2");

        lpsolve55.lpsolve.print_lp(lp);
        lpsolve55.lpsolve.set_add_rowmode(lp, true);
        lpsolve55.lpsolve.add_constraintex(lp, 2, ref Row[0], ref Col[0],lpsolve55.lpsolve.lpsolve_constr_types.LE, 0);
        lpsolve55.lpsolve.set_add_rowmode(lp, false);
        //lpsolve55.lpsolve.print_lp(lp);

        //txtMIP.Text = lp.ToString();
        lpsolve55.lpsolve.print_objective(lp);
        lpsolve55.lpsolve.set_minim(lp);
        lpsolve55.lpsolve.print_lp(lp);
        lpsolve55.lpsolve.lpsolve_return lp_result;
        lp_result = lpsolve55.lpsolve.solve(lp);
        txtMIP.Text = lp_result.ToString();
        
        
        //lpsolve_return solve(int lp)
        

        lpsolve55.lpsolve.write_lp(lp, "C://test2//lp.lp");
        //lpsolve55.lpsolve.write_mps(lp, "C://test1//lp.mps");
        lpsolve55.lpsolve.set_outputfile(lp, "C://test2//result.txt");
        lpsolve55.lpsolve.delete_lp(lp);
        lp = lpsolve55.lpsolve.read_LP("C://test2//lp.lp", 0, "test");
        if (lp == 0)
        {
             txtMIP.Text = "Can't find lp.lp, stopping";
              //return;
        }
        else
            lpsolve55.lpsolve.set_outputfile(lp, "C://test2//result.txt");
        
  }
    private void Solve()
    {
        lpsolve55.lpsolve.Init( "C://Users//delnavaz//Documents//Visual Studio 2008//WebSites//myQL//Bin" ) ;
            //"C:\\Program Files (x86)\\LPSolve IDE\\LpSolveIDE.exe" + "\\bin");

        /*StreamWriter sw = new StreamWriter(Path + "\\lp_solve\\model.txt");
        sw.WriteLine(txtModel.Text);
        sw.Close();

        result.InnerHtml = "";

        int lp = 0, Nrows, Ncolumns, i;
        lpsolve.lpsolve_return ret;
        bool ok;

        if (optLP.Checked)
            lp = LoadLPModel(formats.lp);
        if (optCPLEX.Checked)
            lp = LoadLPModel(formats.cplex);
        if (optLINDO.Checked)
            lp = LoadLPModel(formats.lindo);
        if (optXPRESS.Checked)
            lp = LoadLPModel(formats.xpress);
        if (optMPS.Checked)
            lp = LoadLPModel(formats.mps);
        if (optFREEMPS.Checked)
            lp = LoadLPModel(formats.freemps);
        if (optMathProg.Checked)
            lp = LoadLPModel(formats.mathprog);
        if (optZIMPL.Checked)
            lp = LoadLPModel(formats.zimpl);
        if (optLPFML.Checked)
            lp = LoadLPModel(formats.lpfml);
        if (optDIMACS.Checked)
            lp = LoadLPModel(formats.dimacs);
        if (lp == 0)
        {
            result.InnerHtml += "Unable to load model in lp_solve. There is probably a syntax error.";
        }
        else
        {
            const int timeout = 5;

            Nrows = lpsolve.get_Nrows(lp);
            Ncolumns = lpsolve.get_Ncolumns(lp);
            result.InnerHtml += "<table>";

            result.InnerHtml += "<tr>";
            result.InnerHtml += "<td>Number of rows:</td><td>" + Nrows.ToString() + "</td>";
            result.InnerHtml += "</tr>";

            result.InnerHtml += "<tr>";
            result.InnerHtml += "<td>Number of columns:</td><td>" + Ncolumns.ToString() + "</td>";
            result.InnerHtml += "</tr>";

            lpsolve.set_timeout(lp, timeout);
            ret = lpsolve.solve(lp);
            ok = (ret == lpsolve.lpsolve_return.OPTIMAL);

            result.InnerHtml += "<tr>";
            result.InnerHtml += "<td>Return code:</td><td>" + ret.ToString() + ((ret == lpsolve.lpsolve_return.TIMEOUT) ? " (a timeout of " + timeout.ToString() + " seconds was set)" : "") + "</td>";
            result.InnerHtml += "</tr>";

            if (ok)
            {
                result.InnerHtml += "<tr>";
                result.InnerHtml += "<td>Time to solve:</td><td>" + lpsolve.time_elapsed(lp).ToString() + " seconds</td>";
                result.InnerHtml += "</tr>";

                result.InnerHtml += "<tr>";
                result.InnerHtml += "<td>Objective value:</td><td>" + lpsolve.get_objective(lp).ToString() + "</td>";
                result.InnerHtml += "</tr>";
            }
            result.InnerHtml += "</table>";

            if (ok)
            {
                /* variables */
                /*result.InnerHtml += "<br><table><tr><td>Variables:</td></tr></table>";

                result.InnerHtml += "<table border='1'>";

                result.InnerHtml += "<tr>";
                result.InnerHtml += "<th>" + "Name" + "</th><th>" + "Value" + "</th>";
                result.InnerHtml += "</tr>";

                for (i = 1; i <= Ncolumns; i++)
                {
                    result.InnerHtml += "<tr>";
                    result.InnerHtml += "<td>" + lpsolve.get_col_name(lp, i) + "</td><td>" + lpsolve.get_var_primalresult(lp, Nrows + i).ToString() + "</td>";
                    result.InnerHtml += "</tr>";
                }
                result.InnerHtml += "</table>";

                /* constraints */
                /*result.InnerHtml += "<br><table><tr><td>Constraints:</td></tr></table>";

                result.InnerHtml += "<table border='1'>";

                result.InnerHtml += "<tr>";
                result.InnerHtml += "<th>" + "Name" + "</th><th>" + "Value" + "</th>";
                result.InnerHtml += "</tr>";

                for (i = 1; i <= Nrows; i++)
                {
                    result.InnerHtml += "<tr>";
                    result.InnerHtml += "<td>" + lpsolve.get_row_name(lp, i) + "</td><td>" + lpsolve.get_var_primalresult(lp, i).ToString() + "</td>";
                    result.InnerHtml += "</tr>";
                }
                result.InnerHtml += "</table>";
            }
            lpsolve.delete_lp(lp);
        }*/
    }
    protected void chResponseTime_CheckedChanged(object sender, EventArgs e)
    {

    }
    protected void RBR_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
    protected void btnBrowsA_Click(object sender, EventArgs e)
    {
        
        Response.Redirect("Default.aspx"); //Browsing.aspx");
    }
}
