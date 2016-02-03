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
using System.Text;
using System.Xml;
using System.IO;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Xml.Linq;
using lpsolve55;

public partial class result : System.Web.UI.Page
{
    public static int offers_cnt = 18;
    public static int attr_cnt = 5;
    public S_Obj[] S_assign_Xml = new S_Obj[offers_cnt+1];
    public string[] attr_Order = new string[attr_cnt]; //{ "reliability", "responsetime", "price", "authentication" };
    public XmlDocument xmlDoc = new XmlDocument();

    public S_Obj request = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
    public S_Obj[] Single_result = new S_Obj[offers_cnt];
    public S_Obj[] Range_result = new S_Obj[offers_cnt];
    public S_Obj[] Fuzzy_result = new S_Obj[offers_cnt];
    public S_Obj[] whole_result = new S_Obj[offers_cnt];
    public S_Obj[] AllHard_result = new S_Obj[offers_cnt];
    public S_Obj[] whole_result_soft = new S_Obj[offers_cnt];
    public S_Obj[] Intersection_result = new S_Obj[offers_cnt];
    public string Qname6 = "";
    public XmlDocument xmlDoc2 = new XmlDocument();
    public XmlElement QoSConstraintsElement;

    
    protected void Page_Load(object sender, EventArgs e)
    {
        string attrList = "*response time*availability*latency*throughput";
        //string attrList = "*response time*reliability*price*authentication";
        //string attrList = Session["attrList"].ToString();

        Qname6 = "commerce"; //Session["keyword"].ToString(); 

        attr_Order = attrList.Split('*');

        string fname = @"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL2.xml"; //"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\testCommerce\\myQL2.xml";
        DataSet ds = new DataSet(); 
        ds.ReadXml(fname); 
        GridView1.DataSource = ds; 
        GridView1.DataBind();

        string fname_req = @"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL2.xml"; //"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\testCommerce\\myQL2.xml";
        XDocument xmlDocReq = XDocument.Load(fname_req);
        for (int k = 0; k < GridView1.Rows.Count; k++)
        {
            TextBox txtDetails = (TextBox)GridView1.Rows[k].FindControl("txtDetails");
            //Label lblname = (Label)GridView1.Rows[k].FindControl("lblname");


            var R_q = from c in xmlDocReq.Descendants("QoSConstraint")
                      select (string)c.Element("name");
            var R_qq = from c in xmlDocReq.Descendants("values")
                       select " from :" + (string)c.Element("from") + " to:  " + (string)c.Element("to");

            //lblname.Text = R_q.ToList().ElementAt(k);
            txtDetails.Text = R_qq.ToList().ElementAt(k);
            //string strVal = R_qq.ToList().ElementAt(k);
            //txtDetails.Text = strVal.Substring(strVal.IndexOf("from"));

        }
        ds.Dispose();

        bind_gv2();

    }
    protected void btnNext_Click(object sender, EventArgs e)
    {

    }
    protected void btnPrevious_Click(object sender, EventArgs e)
    {

    }
    protected void btnCancel_Click(object sender, EventArgs e)
    {
        Response.Redirect("step1.aspx");
    }


    protected void Button1_Click(object sender, EventArgs e)
    {

        DateTime startTime = DateTime.Now;
        //txtTime.Text = startTime.ToString();
        selection();
        DateTime stopTime = DateTime.Now;
        //txtTime.Text += stopTime.ToString();
        TimeSpan tolalDuration = stopTime - startTime;
        txtTime.Text = tolalDuration.ToString();
    }

    void assign_rank()
    {
        string[] str_file = { "", "", "", "", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "" , "", "", "" , "" , ""  
        , "", "", "", "", "", "", "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , ""  , "" , "" , "", "", ""
        , "", "", "", "", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "" , "", "", "" , "" , "" 
        , "", "", "", "", "", "", "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , "" , ""  , "" , "" , "", "", "" 
        , "", "", "", "", "", "", "", "", "" , "", "", "", "", "", "", "", "", "", "" , "", "", "" , "" , ""  
        , "", "", "", "", "", "", "" , "" , "" , "" , "" , "" }; //, "" , "" , "" , "" , "" , "" , "" , "" , ""  , "" , "" , "", "", "", 
          
        StreamReader textReader = File.OpenText("C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\input.txt");
        for (int i = 0; i <= offers_cnt; i++)
        {
            StringBuilder str_Xml = new StringBuilder();
            S_assign_Xml[i] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

            str_file[i] = textReader.ReadLine();
            str_Xml.AppendLine(readfromxmlfile(str_file[i], str_Xml, S_assign_Xml[i]));
        }
        textReader.Close();
        
        //normalization 
       /* double[] responsetime = new double[offers_cnt];
        double minNorm = 100.0, maxNorm = 0.0;
        for (int r = 0; r < offers_cnt; r++)
        {
            responsetime[r] = Convert.ToDouble(S_assign_Xml[r].responsetime.val);
            if (minNorm > responsetime[r])
                minNorm = responsetime[r];
            if (maxNorm < responsetime[r])
                maxNorm = responsetime[r];
        }
        for (int r = 0; r < offers_cnt; r++)
        {
            responsetime[r] = (maxNorm - responsetime[r]) / (maxNorm - minNorm);
            S_assign_Xml[r].responsetime.val = responsetime[r];
            TextBox1.Text += r + ":" + responsetime[r].ToString() + "\n";
        }*/
        //end normalization 

        double rank_S = 0.0;
        double diff_max = 0.0 , diff_min = 0.0;
        double[] diffarr = new double[offers_cnt];
        //double[] diffarr2 = new double[offers_cnt];
        double[] sort_diff = new double[offers_cnt];
        for (int i = 0; i < GridView2.Rows.Count; i++)
        {
            Label lblSID = (Label)GridView2.Rows[i].FindControl("lblSID");
            TextBox txtRank = (TextBox)GridView2.Rows[i].FindControl("txtRank");
            rank_S = ranking(lblSID.Text);
            txtRank.Text = rank_S.ToString();
            diffarr[i] = rank_S;
            //updateRank(lblSID.Text, rank_S);
        }
        sort_diff = (double[])diffarr.Clone();
        //sort_diff2 = (double[])diffarr.Clone();
        Array.Sort(sort_diff);
        diff_max = sort_diff[offers_cnt - 1];
        diff_min = sort_diff[0];
        //Array.Sort(sort_diff2);

        for (int i = 0; i < offers_cnt; i++)
        {
            sort_diff[i] = (diff_max - sort_diff[i]) / (diff_max + diff_max);
            /*for (int h = 0; h < offers_cnt; h++)
            {
                if (diffarr[h] == sort_diff[i])
                {
                    TextBox3.Text += "S" + (h+1).ToString() +":" + sort_diff[i].ToString() + "\n";
                    break;
                }
            }
            TextBox5.Text += "S" + i.ToString() + ":" + diffarr[i].ToString() + "\n";
            TextBox4.Text += sort_diff[i].ToString() + "\n";*/
        }
    }

    double ranking(string SID)
    {
        double s_r = 0.0, req_r = 0.0;
        double diff = 0.0;
        double[] original_diff = new double[offers_cnt];
        //string[] s_name = { "S0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }; //, "", "", "", "", "", "", "", ""};

        req_r = lp_solve(S_assign_Xml[0], 0);
        int ind = Convert.ToInt32(SID.Substring(SID.IndexOf("S")+1));
        s_r = lp_solve(S_assign_Xml[ind], ind);
        diff = Math.Abs(req_r - s_r);
        return diff;
    }

    string readfromxmlfile(string str_filename, StringBuilder strXml, S_Obj S_assign)
    {
        XmlTextReader reader = new XmlTextReader(str_filename);
        string strInnerXML = "";
        while (reader.Read())
        {
            switch (reader.NodeType)
            {
                case XmlNodeType.Element:
                    strXml.Append("<" + reader.Name);
                    strXml.AppendLine(">");
                    break;
                case XmlNodeType.Text:
                    strXml.AppendLine(reader.Value);
                    if (reader.Value.Equals("reliability"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.val = strInnerXML;
                        S_assign.reliability.upper_val = strInnerXML;
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.lower_val = strInnerXML;
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.reliability.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.reliability.val.ToString());
                    }
                    if (reader.Value.Equals("response time"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.lower_val = strInnerXML;
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.val = strInnerXML;
                        S_assign.responsetime.upper_val = strInnerXML;
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.responsetime.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.responsetime.val.ToString());
                    }

                    if (reader.Value.Equals("price"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.val = strInnerXML.ToString();
                        S_assign.price.upper_val = strInnerXML;
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.lower_val = strInnerXML;
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.price.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.price.val.ToString());
                    }
                    if (reader.Value.Equals("authentication"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.val = Convert.ToBoolean(strInnerXML);
                        S_assign.authentication.upper_val = Convert.ToBoolean(strInnerXML);
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.lower_val = Convert.ToBoolean(strInnerXML);
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.authentication.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.authentication.val.ToString());
                    }
                    if (reader.Value.Equals("availability"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.val = strInnerXML.ToString();
                        S_assign.availability.lower_val = strInnerXML.ToString();
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.upper_val = strInnerXML.ToString();
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.availability.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.availability.val.ToString());
                    }
                    if (reader.Value.Equals("latency"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.val = strInnerXML.ToString();
                        S_assign.latency.lower_val = strInnerXML.ToString();
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.upper_val = strInnerXML.ToString();
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.latency.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.latency.val.ToString());
                    }
                    if (reader.Value.Equals("throughput"))
                    {
                        reader.ReadToFollowing("from");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.val = strInnerXML.ToString();
                        S_assign.throughput.lower_val = strInnerXML.ToString();
                        reader.ReadToFollowing("to");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.upper_val = strInnerXML.ToString();
                        reader.ReadToFollowing("type");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.type = strInnerXML;
                        reader.ReadToFollowing("tendency");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.tendency = strInnerXML;
                        reader.ReadToFollowing("preference");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.pref_Order = Convert.ToInt32(strInnerXML);
                        reader.ReadToFollowing("relaxation");
                        strInnerXML = reader.ReadInnerXml();
                        S_assign.throughput.relax_order = Convert.ToInt32(strInnerXML);
                        strXml.AppendLine(S_assign.throughput.val.ToString());
                    }
                    break;
                case XmlNodeType.EndElement:
                    strXml.Append("</" + reader.Name);
                    strXml.AppendLine(">");
                    break;

            }
        }
        strXml.AppendLine();
        reader.Close();
        return strXml.ToString();
        //return S_assign;
    }

    double lp_solve(S_Obj S_beRanked, int counter)
    {
        string attr_name = "";
        string[] uq = new string[1000];
        double rank_whole = 0.0;
        double min = 0.0, max = 0.0;
        string str_tendency = "neutral";
        for (int k = 0; k < attr_Order.Count(); k++)
        {
            attr_name = attr_Order[k].ToString();
            if (attr_name != "")
            {
                str_tendency = S_beRanked.find_tendency(attr_name).ToString();
                int lp;
                lp = lpsolve55.lpsolve.make_lp(0, 2);
                if (attr_name == "response time")
                    lpsolve55.lpsolve.set_col_name(lp, 1, "responsetime");
                else 
                    lpsolve55.lpsolve.set_col_name(lp, 1, attr_name);            //lpsolve55.lpsolve.set_col_name(lp, 1, "x3"); //x3:price
                
                lpsolve55.lpsolve.set_col_name(lp, 2, "c");
                if (str_tendency == "negative")
                    lpsolve55.lpsolve.set_maxim(lp);
                if (str_tendency == "positive")
                    lpsolve55.lpsolve.set_minim(lp);
                if (str_tendency == "neutral")
                    lpsolve55.lpsolve.set_maxim(lp);

                max = find_max(attr_name);
                min = find_min(attr_name);
                double w = find_weight(attr_name, S_beRanked.find_pref_order(attr_name));
                writeXML_weights(attr_name, w);
                uq[k] = (w / (max - min)).ToString();
                uq[k] += " " + (-w * min / (max - min)).ToString();
                 
                lpsolve55.lpsolve.str_set_obj_fn(lp, uq[k].ToString()); // "0.1 -8");

                if (str_tendency == "positive")
                    lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.GE, Convert.ToDouble(S_beRanked.find_lower_val(attr_name)));
                if (str_tendency == "negative")
                    lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.LE, Convert.ToDouble(S_beRanked.find_upper_val(attr_name)));
                if (str_tendency == "neutral")
                {
                    if (S_beRanked.find_val(attr_name).Equals(true)) //.Equals("true"))
                        lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.EQ, 1.0);
                    else
                        lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.EQ, 0.0);
                }
                
                lpsolve55.lpsolve.str_add_constraint(lp, "0 1", lpsolve.lpsolve_constr_types.EQ, 1);

                lpsolve55.lpsolve.set_outputfile(lp, "C://test4//" + attr_name + "_S" + counter.ToString() + "_result.lp"); //"test_lp2.lp");
                lpsolve55.lpsolve.write_lp(lp, "C://test4//" + attr_name + "_S" + counter.ToString() + ".lp"); //"test_lp.lp");

                /*int res = lpsolve55.lpsolve.read_LP("C://test3//test_lp.lp" ,0, "ret_lp");
                lpsolve55.lpsolve.lpsolve_return lp_result;
                lp_result = lpsolve55.lpsolve.solve(res);*/

                lpsolve55.lpsolve.solve(lp);
                lpsolve55.lpsolve.print_objective(lp);
                lpsolve55.lpsolve.print_solution(lp, 2);
                lpsolve55.lpsolve.print_constraints(lp, 2);

                double rank = lpsolve55.lpsolve.get_objective(lp);
                /*if (str_tendency == "negative")
                    rank = -rank;*/
                string str_Sname = "";
                if (counter == 0)
                    str_Sname = "req";
                else
                    str_Sname = "S" + counter.ToString();
                //TextBox1.Text += str_Sname + ":" + rank.ToString() + "\n";
                rank_whole += rank;
                lpsolve55.lpsolve.delete_lp(lp);
            }
        }
        TextBox1.Text += "rank :" + rank_whole.ToString() + "\n";
        return rank_whole;
    }

    double find_weight(string q_name, int q_pref)
    {
        double ret = 0.0;
        ret = (find_max_w() - q_pref + 0.1) / (find_max_w() - find_min_w());
        //ret = 1.0/q_pref * find_max_w();
        TextBox2.Text += "w:" + ret + "\n"; // +"=" + q_pref.ToString() + "-" + find_min_w().ToString() + "/" + (find_max_w().ToString() + "-" + find_min_w().ToString()) + "\n";
        return ret;
    }

    double find_max_w()
    {
        double m_w = 0.0;
        m_w = S_assign_Xml[0].find_pref_order("price");
        if (m_w < S_assign_Xml[0].find_pref_order("reliability"))
            m_w = S_assign_Xml[0].find_pref_order("reliability");
        if (m_w < S_assign_Xml[0].find_pref_order("response time"))
            m_w = S_assign_Xml[0].find_pref_order("response time");
        if (m_w < S_assign_Xml[0].find_pref_order("authentication"))
            m_w = S_assign_Xml[0].find_pref_order("authentication");
        if (m_w < S_assign_Xml[0].find_pref_order("availability"))
            m_w = S_assign_Xml[0].find_pref_order("availability");
        if (m_w < S_assign_Xml[0].find_pref_order("latency"))
            m_w = S_assign_Xml[0].find_pref_order("latency");
        if (m_w < S_assign_Xml[0].find_pref_order("throughput"))
            m_w = S_assign_Xml[0].find_pref_order("throughput");

        return m_w;
    }
    double find_min_w()
    {
        double m_w = 0.0;
        m_w = S_assign_Xml[0].find_pref_order("price");
        if (m_w > S_assign_Xml[0].find_pref_order("reliability"))
            m_w = S_assign_Xml[0].find_pref_order("reliability");
        if (m_w > S_assign_Xml[0].find_pref_order("response time"))
            m_w = S_assign_Xml[0].find_pref_order("response time");
        if (m_w > S_assign_Xml[0].find_pref_order("authentication"))
            m_w = S_assign_Xml[0].find_pref_order("authentication");
        if (m_w > S_assign_Xml[0].find_pref_order("availability"))
            m_w = S_assign_Xml[0].find_pref_order("availability");
        if (m_w > S_assign_Xml[0].find_pref_order("latency"))
            m_w = S_assign_Xml[0].find_pref_order("latency");
        if (m_w > S_assign_Xml[0].find_pref_order("throughput"))
            m_w = S_assign_Xml[0].find_pref_order("throughput");
        return m_w;
    }
    double find_max(string q_name)
    {
        object max_obj = new object();
        object compare_obj = new object();
        max_obj = S_assign_Xml[0].find_upper_val(q_name);             //S_assign_Xml[0].find_val(q_name);
        for (int a = 0; a < S_assign_Xml.Length; a++)
        {
            compare_obj = S_assign_Xml[a].find_upper_val(q_name); //compare_obj = S_assign_Xml[a].find_val(q_name);
            if (S_assign_Xml[a].find_type(q_name).ToString() == "range")
            {
                if (Convert.ToDouble(max_obj) < Convert.ToDouble(compare_obj))
                    max_obj = compare_obj;
            }
            if (S_assign_Xml[a].find_type(q_name).ToString() == "single")
            {
                if (S_assign_Xml[a].find_upper_val(q_name).ToString() == "false")
                    max_obj = 0.0;
                else
                    if (max_obj.ToString() != "0.0")
                        max_obj = 1.0;

            }

        }
        return Convert.ToDouble(max_obj);
    }
    double find_min(string q_name)
    {
        object min_obj = new object();
        object compare_obj = new object();
        min_obj = S_assign_Xml[0].find_lower_val(q_name);
        for (int a = 1; a < offers_cnt; a++)
        {
            //compare_obj = S_assign_Xml[a].find_val(q_name);
            compare_obj = S_assign_Xml[a].find_lower_val(q_name);
            if (S_assign_Xml[a].find_type(q_name).ToString() == "range")
            {
                if (Convert.ToDouble(min_obj) > Convert.ToDouble(compare_obj))
                    min_obj = compare_obj;
            }
            if (S_assign_Xml[a].find_type(q_name).ToString() == "single")
            {
                if (S_assign_Xml[a].find_lower_val(q_name).ToString() == "true")
                    min_obj = 1.0;
                else
                    if (min_obj.ToString() != "1.0")
                        min_obj = 0.0;

            }
        }
        return Convert.ToDouble(min_obj);
    }

    void writeXML_weights(string name_attr, double wght)
    {
        xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL2.xml");
        writeXML_weight(name_attr, wght , xmlDoc );
        xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL2.xml");

        for (int y = 1; y <= offers_cnt; y++)
        {
            xmlDoc.Load(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\S" + y + ".xml");
            writeXML_weight(name_attr, wght, xmlDoc);
            xmlDoc.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\S" + y + ".xml");
        }

    }

    XmlDocument writeXML_weight(string name_attr, double wght, XmlDocument xmlDoc)
    {
        XmlNode node;
        node = xmlDoc.DocumentElement;

        if (name_attr == "price")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "price")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
        }
        if (name_attr == "reliability")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "reliability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("reliability");
        }
        if (name_attr == "response time")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "response time")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("responsetime");
        }
        if (name_attr == "authentication")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "authentication")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("authentication");
        }
        if (name_attr == "availability")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "availability")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("availability");
        }
        if (name_attr == "latency")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "latency")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }
                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("latency");
        }
        if (name_attr == "throughput")
        {
            foreach (XmlNode node1 in node.ChildNodes)
                foreach (XmlNode node2 in node1.ChildNodes)
                    if (node2.InnerText == "throughput")
                    {
                        foreach (XmlNode node3 in node1.ChildNodes)
                        {
                            if (node3.Name.Equals("weight"))
                            {
                                node1.RemoveChild(node3);
                                break;
                            }

                        }
                        XmlNode newNode = xmlDoc.CreateNode(XmlNodeType.Element, "weight", "");
                        newNode.InnerText = wght.ToString();
                        node1.AppendChild(newNode);
                        break;
                    }
            //update_Element("throughput");
        }
    return xmlDoc;
    }

    void bind_gv2()
    {
        writeXML();

        string fname1 = "C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL.xml";
        DataSet ds1 = new DataSet();
        ds1.ReadXml(fname1);
        GridView2.DataSource = ds1;
        //GridView2.Sort("rank", SortDirection.Ascending);
        GridView2.DataBind();

        int i = 0;
        for (int k = 0; k < GridView2.Rows.Count; k++)
        {
            i++;
            //Label lblInfo = (Label)GridView2.Rows[k].FindControl("lblInfo");
            Label lblSID = (Label)GridView2.Rows[k].FindControl("lblSID");
            //TextBox txtInfo = (TextBox)GridView2.Rows[k].FindControl("txtInfo");
            /*string fname_S2 = @"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\testDevelopment\\S" + i + ".xml";
            XDocument xmlDoc = XDocument.Load(fname_S2);
            var q = from c in xmlDoc.Descendants("QoSConstraint")
                    select (string)c.Element("name");
            var qq = from c in xmlDoc.Descendants("values")
                     select " from :" + (string)c.Element("from") + " to:  " + (string)c.Element("to");*/

            lblSID.Text = "S" + i;
            /*for (int j = 0; j < q.Count(); j++)
            {
                txtInfo.Text += q.ToList().ElementAt(j) + "\n";

                txtInfo.Text += qq.ToList().ElementAt(j) + "\n";
            }*/

        }
        System.GC.Collect();
        assign_rank();
    }

    void selection()
    {
        request = S_assign_Xml[0];
        for (int p = 0; p < offers_cnt; p++)
        {
            Range_result[p] = selection_Hard(S_assign_Xml[p]);

            //if (matching(S_assign_Xml[p], p))                Range_result[p] = S_assign_Xml[p]; 
            //Fuzzy_result[i] = selection(S_assign_Xml[i]);            
        }
        AllHard_result = Range_result;
        for (int h = 1; h < offers_cnt; h++)
            insert(AllHard_result[h], h, "tbl_Hard_selected");

        for (int j = 0; j < GridView2.Rows.Count; j++)
        {
            Label lblSID = (Label)GridView2.Rows[j].FindControl("lblSID");
            TextBox txtRank = (TextBox)GridView2.Rows[j].FindControl("txtRank");
            updateRank(lblSID.Text, Convert.ToDouble(txtRank.Text), "tbl_Hard_selected");
        }


       for (int i = 0; i < offers_cnt; i++)
        {
           Single_result[i] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

            Single_result[i] = selection_Single(S_assign_Xml[i]);
            Range_result[i] = selection_Range(S_assign_Xml[i]);

            whole_result[i] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        }

        whole_result = intersection(Single_result, Range_result);

        for (int h = 1; h < offers_cnt; h++)
            insert(whole_result[h], h, "tbl_Selected");

        delete("tbl_Selected", "tbl_Hard_selected");

        for (int j = 0; j < GridView2.Rows.Count; j++)
        {
            Label lblSID = (Label)GridView2.Rows[j].FindControl("lblSID");
            TextBox txtRank = (TextBox)GridView2.Rows[j].FindControl("txtRank");
            updateRank(lblSID.Text, Convert.ToDouble(txtRank.Text), "tbl_Selected");
        }

        int empty_whole = 0;
        for (int p = 1; p < offers_cnt; p++)
        {
            if (whole_result[p].isEmpty(whole_result[p]) == false)
            {
                empty_whole++;
                //print_S(whole_result[p], p);
                whole_result_soft[p] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
            }
        }
        if (empty_whole == 0) // it means the whole intersection is empty
            Label2.Text = "There is no match for your requirements please modify your constraints"; //other works
        //startdate
        else
        {
            Label2.Text = empty_whole.ToString() + "   there is some match"; //procede with checking soft constraints
            for (int r = 1; r < attr_cnt; r++)
            {
                for (int j = 1; j < offers_cnt; j++)
                {
                    Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                    Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                                            new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                    Single_result[j] = selection_Single_Soft(whole_result[j],r);
                    Range_result[j] = selection_Range_Soft(whole_result[j],r);
                }
            }
        }

        whole_result_soft = intersection(Single_result, Range_result);

        int empty_whole2 = 0;
        for (int p = 1; p < offers_cnt; p++)
        {
            if (whole_result_soft[p].isEmpty(whole_result_soft[p]) == false)
            {
                ++empty_whole2;
            }
        }
        

        if (empty_whole2 == 0) // it means the whole intersection of soft constraints is empty
        {
            Label2.Text = "There is no match for the soft constraints"; //relaxation
            
            for (int r = 1; r <= attr_cnt; r++)
            {
                for (int g = 1; g < offers_cnt; g++)
                {
                    if (r == whole_result[g].reliability.relax_order)
                    {
                        //whole_result_soft[g].reliability.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].responsetime.relax_order)
                    {
                        //whole_result_soft[g].responsetime.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].price.relax_order)
                    {
                        //whole_result_soft[g].price.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].authentication.relax_order)
                    {
                        //whole_result_soft[g].authentication.relax_order = -1;
                        for (int j = 0; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].availability.relax_order)
                    {
                        //whole_result_soft[g].availability.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].latency.relax_order)
                    {
                        //whole_result_soft[g].latency.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                    if (r == whole_result[g].throughput.relax_order)
                    {
                        //whole_result_soft[g].throughput.relax_order = -1;
                        for (int j = 1; j < offers_cnt; j++)
                        {
                            Single_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
                            Range_result[j] = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));

                            Single_result[j] = selection_Single_Soft(whole_result[j], r);
                            Range_result[j] = selection_Range_Soft(whole_result[j], r);
                        }
                    }
                } //insert first group relaxed results
            }
            whole_result_soft = intersection(Single_result, Range_result);
            
            for (int h = 1; h < offers_cnt; h++)
                insert(whole_result_soft[h], h, "tbl_Selected");

            delete("tbl_Selected", "tbl_Hard_selected");
            

            for (int j = 0; j < GridView2.Rows.Count; j++)
            {
                Label lblSID = (Label)GridView2.Rows[j].FindControl("lblSID");
                TextBox txtRank = (TextBox)GridView2.Rows[j].FindControl("txtRank");
                updateRank(lblSID.Text, Convert.ToDouble(txtRank.Text), "tbl_Selected");
            }
        }
        
        else
        {
            Label2.Text += empty_whole2.ToString() + "   there is some match with soft constraint"; //procede with checking soft constraints
        }
        //stopdate

        //textReader.Close();
        /*for (int h = 1; h < offers_cnt; h++)
        {
            //insert(whole_result[h], h);
            insert (whole_result_soft[h] , h);
            
        }
        for(int j=1; j<GridView2.Rows.Count; j++)
        {
            Label lblSID = (Label)GridView2.Rows[j].FindControl("lblSID");
            TextBox txtRank = (TextBox)GridView2.Rows[j].FindControl("txtRank");
            //rank_S = ranking(lblSID.Text);
            

            updateRank(lblSID.Text , Convert.ToDouble(txtRank.Text));
        }*/
        GridView4.DataBind();
        GridView5.DataBind();
        System.GC.Collect();
    }

    void print_S(S_Obj S_print, int cnt)
    {
        string str_Sname = "";
        if (cnt == 0)
            str_Sname = "req";
        else
            str_Sname = "S" + cnt.ToString();

        TextBox4.Text += str_Sname + ":" + S_print.reliability.val.ToString() + "  " + S_print.responsetime.val.ToString() + "  " +
                        S_print.price.val.ToString() + "  " + S_print.authentication.val.ToString() +
                        S_print.availability.val.ToString() + "  " + S_print.latency.val.ToString() +
                        S_print.throughput.val.ToString() + "\n";
    }

    void delete(string tblname1, string tblname2)
    {
        SqlConnection myConnection = new SqlConnection("server=localhost;database=test;Trusted_Connection=Yes");
        SqlCommand myCommand = new SqlCommand();
        /*string str_Sname = "";
        if (cnt == 0)
            str_Sname = "req";
        else
            str_Sname = "S" + cnt.ToString();
        */


            string deleteCmd;
            deleteCmd = "delete from " + tblname1 + " WHERE (Sname IN (SELECT Sname  FROM " + tblname2 + "))";
            myCommand = new SqlCommand(deleteCmd, myConnection);
            myCommand.Connection.Open();
            myCommand.ExecuteNonQuery();
            myCommand.Connection.Close();
    }

    void insert(S_Obj tobe_inserted, int cnt, string tblname)
    {
        SqlConnection myConnection = new SqlConnection("server=localhost;database=test;Trusted_Connection=Yes");
        SqlCommand myCommand = new SqlCommand();
        string insertCmd ;

        string str_Sname = "";
        if (cnt == 0)
            str_Sname = "req";
        else
            str_Sname = "S" + cnt.ToString();

        insertCmd = "insert into " + tblname + " values (@Sname, @rank, @responsetime, @availability, @latency, @throughput)";
        myCommand = new SqlCommand(insertCmd, myConnection);

        SqlParameter SnameParam = new SqlParameter("@Sname", SqlDbType.NVarChar, 200);
        SnameParam.Value = str_Sname;


        SqlParameter rankParam = new SqlParameter("@rank", SqlDbType.Float);
        rankParam.Value = 0.0;

        SqlParameter responsetimeParam = new SqlParameter("@responsetime", SqlDbType.NVarChar, 50);
        responsetimeParam.Value = tobe_inserted.responsetime.val;

        SqlParameter availabilityParam = new SqlParameter("@availability", SqlDbType.NVarChar, 50);
        availabilityParam.Value = tobe_inserted.availability.val;

        SqlParameter latencyParam = new SqlParameter("@latency", SqlDbType.NVarChar, 50);
        latencyParam.Value = tobe_inserted.latency.val;

        SqlParameter throughputParam = new SqlParameter("@throughput", SqlDbType.NVarChar, 50);
        throughputParam.Value = tobe_inserted.throughput.val;

        myCommand.Parameters.Add(SnameParam);
        myCommand.Parameters.Add(rankParam);
        myCommand.Parameters.Add(responsetimeParam);
        myCommand.Parameters.Add(availabilityParam);
        myCommand.Parameters.Add(latencyParam);
         myCommand.Parameters.Add(throughputParam);

   
            myCommand.Connection.Open();
            myCommand.ExecuteNonQuery();
            myCommand.Connection.Close();
   
       if (responsetimeParam.Value.Equals("0") && availabilityParam.Value.Equals("0") && 
           latencyParam.Value.Equals("0") && throughputParam.Value.Equals("0"))
        {
            string deleteCmd;
            deleteCmd = "delete from " + tblname + " WHERE (responsetime = '0') AND (availability = '0') AND (latency = '0') AND (throughput = '0') ";
            myCommand = new SqlCommand(deleteCmd, myConnection);
            myCommand.Connection.Open();
            myCommand.ExecuteNonQuery();
            myCommand.Connection.Close();
        }


        /*TextBox4.Text += str_Sname + ":" + S_print.reliability.val.ToString() + "  " + S_print.responsetime.val.ToString() + "  " +
                        S_print.price.val.ToString() + "  " + S_print.authentication.val.ToString() +
                        S_print.availability.val.ToString() + "  " + S_print.latency.val.ToString() +
                        S_print.throughput.val.ToString() + "\n";*/
    }

    void updateRank(string str_sname, double rank, string tblname)
    {
        SqlConnection myConnection = new SqlConnection("server=localhost;database=test;Trusted_Connection=Yes");
        SqlCommand myCommand = new SqlCommand();
        string updateCmd ;

        /*string str_Sname = "";
        if (cnt == 0)
            str_Sname = "req";
        else
            str_Sname = "S" + cnt.ToString();*/

        updateCmd = "update " + tblname + " set rank = @rank where Sname = @Sname";
        myCommand = new SqlCommand(updateCmd, myConnection);

        SqlParameter SnameParam = new SqlParameter("@Sname", SqlDbType.NVarChar, 200);
        SnameParam.Value = str_sname;
        SqlParameter rankParam = new SqlParameter("@rank", SqlDbType.Float);
        rankParam.Value = rank;
        myCommand.Parameters.Add(SnameParam);
        myCommand.Parameters.Add(rankParam);
        myCommand.Connection.Open();
        myCommand.ExecuteNonQuery();
        myCommand.Connection.Close();
    }

    S_Obj selection_Single(S_Obj selected)
    {
        S_Obj result_single = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        //int[] pref_Order = { 3, 2, 1, 3};
        int[] relax_Order = new int[4];
        string attr_name = "";
        
        for (int k = 0; k < 4; k++)
        {
            attr_name = attr_Order[k].ToString();
            if (attr_name != "")
            {
                relax_Order[k] = selected.find_relax_order(attr_name);
                if (relax_Order[k] == 0) //find hard constraints
                {
                    switch (selected.find_type(attr_name).ToString())
                    {
                        case "single":
                            bool res_single = single(selected.find_val(attr_name), request.find_val(attr_name));
                            if (res_single == true)
                                result_single = selected;
                            break;
                    }
                }
            }
        }
        return result_single; //result_fuzzy; //result_range             
    }

    S_Obj selection_Single_Soft(S_Obj selected, int r_Order)
    {
        S_Obj result_single = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        //int[] pref_Order = { 3, 2, 1, 3};
        int[] relax_Order = new int[attr_cnt];
        string attr_name = "";

        for (int k = 0; k < attr_cnt; k++)
        {
            attr_name = attr_Order[k].ToString();
            relax_Order[k] = selected.find_relax_order(attr_name);
            if (relax_Order[k] == r_Order) //find soft constraints
            {
                switch (selected.find_type(attr_name).ToString())
                {
                    case "single":
                        bool res_single = single(selected.find_val(attr_name), request.find_val(attr_name));
                        if (res_single == true)
                            result_single = selected;
                        break;
                }
            }
        }
        return result_single; //result_fuzzy; //result_range             
    }

    S_Obj selection_Range_Soft(S_Obj selected, int r_Order)
    {
        S_Obj result_range = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        //int[] pref_Order = { 3, 2, 1, 3};
        int[] relax_Order = new int[attr_cnt];
        string attr_name = "";
        //int flag = 0;
        for (int k = 0; k < attr_cnt; k++)
        {
            attr_name = attr_Order[k].ToString();
            relax_Order[k] = selected.find_relax_order(attr_name);
            if (relax_Order[k] == r_Order) //find soft constraints
            {
                switch (selected.find_type(attr_name).ToString())
                {
                    case "range":
                        bool res_range =
                                range2(attr_name, selected.find_tendency(attr_name).ToString(), selected.find_pref_order(attr_name),
                                        request.find_upper_val(attr_name), request.find_lower_val(attr_name),
                                         selected.find_upper_val(attr_name), selected.find_lower_val(attr_name)); 

                        /*    range(request.find_upper_val(attr_name) , request.find_lower_val(attr_name), 
                            selected.find_lower_val(attr_name) , selected.find_upper_val(attr_name), selected.find_tendency(attr_name));*/
                        
                    /*if (res_range == false)
                            flag = 1;*/
                        result_range = selected;
                        break;
                }
            }
        }
        /*if (flag == 0)
            result_range = selected;*/

        return result_range; //result_fuzzy; //result_range             
    }


    S_Obj selection_Hard(S_Obj selected)
    {
        S_Obj result_range = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        int[] relax_Order = new int[attr_cnt];
        string attr_name = "";
        bool res_range = false;
        int flag = 0;

        for (int k = 0; k < attr_cnt; k++)
        {
            attr_name = attr_Order[k].ToString();
            if (attr_name != "")
            {
                    switch (selected.find_type(attr_name).ToString())
                    {
                        case "range":
                            res_range =
                                range2(attr_name, selected.find_tendency(attr_name).ToString(), selected.find_pref_order(attr_name),
                                        request.find_upper_val(attr_name), request.find_lower_val(attr_name),
                                         selected.find_upper_val(attr_name), selected.find_lower_val(attr_name));
                            
                                /*range(request.find_upper_val(attr_name), request.find_lower_val(attr_name),
                                                   selected.find_upper_val(attr_name), selected.find_lower_val(attr_name),
                                                   selected.find_tendency(attr_name));*/
                            if (res_range == false)
                                flag = 1;
                            break;
                    }

            }
        }
        if (flag == 0)
            result_range = selected;
        return result_range;
    }

    S_Obj selection_Range(S_Obj selected)
    {
        S_Obj result_range = new S_Obj(new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"),
                                        new attr_Obj("range", 0, 0, "0", "0", "0", "positive"));
        //int[] pref_Order = { 3, 2, 1, 3};
        int[] relax_Order = new int[attr_cnt];
        string attr_name = "";
        bool res_range = false;
        int flag = 0;

        for (int k = 0; k < attr_cnt; k++)
        {
            attr_name = attr_Order[k].ToString();
            if (attr_name != "")
            {
                relax_Order[k] = selected.find_relax_order(attr_name);
                if (relax_Order[k] == 0) //find hard constraints
                {
                    switch (selected.find_type(attr_name).ToString())
                    {
                        case "range":
                            res_range =
                                range2(attr_name, selected.find_tendency(attr_name).ToString(), selected.find_pref_order(attr_name),
                                        request.find_upper_val(attr_name), request.find_lower_val(attr_name),
                                         selected.find_upper_val(attr_name), selected.find_lower_val(attr_name));

                                /*range(request.find_upper_val(attr_name), request.find_lower_val(attr_name),
                                                   selected.find_upper_val(attr_name), selected.find_lower_val(attr_name),
                                                   selected.find_tendency(attr_name));*/
                            if (res_range == false)
                                flag = 1;            
                            break;
                    }
                }
            }
        }
        if (flag == 0)
            result_range = selected;
        return result_range;
    }

    bool single(object req_attr, object S_attr)
    {
        if (req_attr.Equals(S_attr))
            return true;
        else
            return false;
    }

    S_Obj[] intersection(S_Obj[] Single_set, S_Obj[] Range_set) //, S_Obj Fuzzy_set)
    {
        for (int n = 0; n < offers_cnt; n++)
        {
            for (int m = 0; m < offers_cnt; m++)
            {
                if (Single_set[n].isEmpty(Single_set[n]))
                {
                    whole_result[m] = Range_set[m];
                }
                if (Single_set[n] == Range_set[m])
                {
                    whole_result[m] = Range_set[m];
                }
                
            }
        }

        /*if(Single_set.Equals(Range_set))
            ret = true; */
        // the intersection is not empty
        return whole_result;
    }

    bool range2(string a_name, string attr_tend, int pref, object req_up_val, object req_low_val, object S_up_val, object S_low_val)
    {
        bool ret = false;
        double reqMIPP = 0.0, offerMIPP = 0.0;
        
        reqMIPP = lp_solve_matching(a_name, attr_tend, pref, Convert.ToDouble(req_low_val), Convert.ToDouble(req_up_val)); 
        offerMIPP = lp_solve_matching(a_name, attr_tend, pref, Convert.ToDouble(S_low_val) , Convert.ToDouble(S_up_val));
        
        if ((reqMIPP <= offerMIPP) && (attr_tend == "positive"))
            ret = true;
        if ((reqMIPP >= offerMIPP) && (attr_tend == "negative")) 
            ret = true;

        return ret;

    }

    bool range(object req_up_val, object req_low_val, object S_up_val, object S_low_val, object attr_tend)
    {

        if (Convert.ToDouble(req_up_val) == Convert.ToDouble(S_up_val))
        {
            if (req_low_val.Equals(S_low_val) == true)
                return true; //result_range.add(offer[m]);  //exact_match
            if ((Convert.ToDouble(req_low_val) < Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "positive")) //<
                return true; //result_range.add(offer[m]);  //super match		
            if ((Convert.ToDouble(req_low_val) > Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "negative"))  //>
                return true; //result_range.add(offer[m]);  //super match							
        }
        if ((Convert.ToDouble(req_up_val) < Convert.ToDouble(S_up_val)) && (attr_tend.ToString() == "positive"))  //offer is better than request, 95% < availability
        {
            if ((Convert.ToDouble(req_low_val) < Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "positive"))
                return true; //result_range.add(offer[m]);  //super match		
            if (req_low_val == S_low_val)
                return true; //result_range.add(offer[m]);  //super match	
        }
        if ((Convert.ToDouble(req_up_val) > Convert.ToDouble(S_up_val)) && (attr_tend.ToString() == "negative"))  //offer is better than request , e.g. 5msec > response time
        {
            if ((Convert.ToDouble(req_low_val) < Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "negative"))
                return true; //result_range.add(offer[m]);  //super match		
            if (req_low_val == S_low_val)
                return true; //result_range.add(offer[m]);  //super match	
        }
        if ((Convert.ToDouble(req_low_val) <= Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "positive"))  //offer is better than request , e.g. 5msec > response time
        {
            if ((Convert.ToDouble(req_up_val) >= Convert.ToDouble(S_up_val))) 
                return true; //result_range.add(offer[m]);  //super match		
        }
        if ((Convert.ToDouble(req_low_val) <= Convert.ToDouble(S_low_val)) && (attr_tend.ToString() == "negative"))  
        {
            if ((Convert.ToDouble(req_up_val) >= Convert.ToDouble(S_up_val)))
                return true; 
        }
        return false;

    }

    double lp_solve_matching(string attr_name, string str_tendency, int prefOrder, double lowerVal , double upperVal)
    {
        string uq = "";
        double min = 0.0, max = 0.0;
        double MIP_pref = 0.0;

        if (attr_name != "")
        {
            int lp;
            lp = lpsolve55.lpsolve.make_lp(0, 2);
            if (attr_name == "response time")
                lpsolve55.lpsolve.set_col_name(lp, 1, "responsetime");
            else
                lpsolve55.lpsolve.set_col_name(lp, 1, attr_name);            //lpsolve55.lpsolve.set_col_name(lp, 1, "x3"); //x3:price

            lpsolve55.lpsolve.set_col_name(lp, 2, "c");
            if (str_tendency == "negative")
                lpsolve55.lpsolve.set_maxim(lp);
            if (str_tendency == "positive")
                lpsolve55.lpsolve.set_minim(lp);
            if (str_tendency == "neutral")
                lpsolve55.lpsolve.set_maxim(lp);

            max = find_max(attr_name);
            min = find_min(attr_name);
            double w = find_weight(attr_name, prefOrder);
            writeXML_weights(attr_name, w);
            uq = (w / (max - min)).ToString();
            uq += " " + (-w * min / (max - min)).ToString();
            /*uq[k] = ((w * max )/ (max - min)).ToString();
            uq[k] += " " + (-w / (max - min)).ToString();*/

            lpsolve55.lpsolve.str_set_obj_fn(lp, uq.ToString()); // "0.1 -8");

            if (str_tendency == "positive")
                lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.GE, Convert.ToDouble(lowerVal));
            if (str_tendency == "negative")
                lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.LE, Convert.ToDouble(upperVal));
           /* if (str_tendency == "neutral")
            {
                if (S_beRanked.find_val(attr_name).Equals(true)) //.Equals("true"))
                    lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.EQ, 1.0);
                else
                    lpsolve55.lpsolve.str_add_constraint(lp, "1 0", lpsolve.lpsolve_constr_types.EQ, 0.0);
            }*/

            lpsolve55.lpsolve.str_add_constraint(lp, "0 1", lpsolve.lpsolve_constr_types.EQ, 1);

            lpsolve55.lpsolve.set_outputfile(lp, "C://test6//" + attr_name + "_S" + "_result.lp"); //"test_lp2.lp");
            lpsolve55.lpsolve.write_lp(lp, "C://test6//" + attr_name + "_S" + ".lp"); //"test_lp.lp");

            lpsolve55.lpsolve.solve(lp);
            lpsolve55.lpsolve.print_objective(lp);
            lpsolve55.lpsolve.print_solution(lp, 2);
            lpsolve55.lpsolve.print_constraints(lp, 2);

            MIP_pref = lpsolve55.lpsolve.get_objective(lp);

            lpsolve55.lpsolve.delete_lp(lp);
        }
        return MIP_pref;
    }


    protected void GridView2_Sorting(object sender, GridViewSortEventArgs e)
    {

    }

    void writeXML()
    {
        QoSConstraintsElement = xmlDoc2.CreateElement("QoSConstraints");

        for (int g = 0; g < offers_cnt; g++)
             write_Element("test");
    
        xmlDoc2.AppendChild(QoSConstraintsElement);

        xmlDoc2.Save(@"C:\\Users\\delnavaz\\Documents\\Visual Studio 2008\\WebSites\\myQL\\App_Data\\test" + Qname6 + "\\myQL.xml");
    }

    void write_Element(string element_name)
    {
        XmlElement QoSConstraintElement = xmlDoc2.CreateElement("QoSConstraint");
        QoSConstraintsElement.AppendChild(QoSConstraintElement);
        XmlElement priceNameElement = xmlDoc2.CreateElement("name");
        QoSConstraintElement.AppendChild(priceNameElement);
        priceNameElement.InnerText = element_name; // "price";

        XmlElement valueElement = xmlDoc2.CreateElement("values");
        QoSConstraintElement.AppendChild(valueElement);
        XmlElement fromElement = xmlDoc2.CreateElement("from");
        XmlElement toElement = xmlDoc2.CreateElement("to");
        XmlElement typePElement = xmlDoc2.CreateElement("type");
        XmlElement tendencyPElement = xmlDoc2.CreateElement("tendency");
        XmlElement preferencePElement = xmlDoc2.CreateElement("preference");
        XmlElement relaxationPElement = xmlDoc2.CreateElement("relaxation");
        XmlElement unitPElement = xmlDoc2.CreateElement("unit");
        XmlElement weightPElement = xmlDoc2.CreateElement("weight");

        if (element_name.Equals("test"))
        {
           fromElement.InnerText = "0";
           toElement.InnerText = "0";
           typePElement.InnerText = "range";
           unitPElement.InnerText = "%";
           tendencyPElement.InnerText = "negative";
           weightPElement.InnerText = "0";
           preferencePElement.InnerText = "0";
           relaxationPElement.InnerText = "0";
        }

        valueElement.AppendChild(fromElement);
        valueElement.AppendChild(toElement);
        QoSConstraintElement.AppendChild(unitPElement);
        QoSConstraintElement.AppendChild(weightPElement);
        QoSConstraintElement.AppendChild(typePElement);
        QoSConstraintElement.AppendChild(tendencyPElement);
        QoSConstraintElement.AppendChild(preferencePElement);
        QoSConstraintElement.AppendChild(relaxationPElement);

    }
}
