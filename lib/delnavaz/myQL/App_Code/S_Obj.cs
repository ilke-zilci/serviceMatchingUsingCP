using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

/// <summary>
/// Summary description for S_Obj
/// </summary>
public class S_Obj
{

    public attr_Obj reliability = new attr_Obj("range", 0, 0, 0, 0, 0, "positive");
    public attr_Obj responsetime = new attr_Obj("range", 0, 0, 0, 0, 0, "positive");
    public attr_Obj price = new attr_Obj("range", 0, 0, 0, 0, 0, "positive");
    public attr_Obj authentication = new attr_Obj("single", 0, 0, false, false, false, "positive");
    public attr_Obj availability = new attr_Obj("range", 0, 0, 0, 0, 0, "positive");
    public attr_Obj latency = new attr_Obj("range", 0, 0, 0, 0, 0, "negative");
    public attr_Obj throughput = new attr_Obj("range", 0, 0, 0, 0, 0, "positive");
       

    public object find_val(string str_name)
    {
        object ret = null; 
        switch (str_name)
        {
            case "reliability": ret = this.reliability.val; break;
            case "response time": ret = this.responsetime.val; break;
            case "price": ret = this.price.val; break;
            case "authentication": ret = this.authentication.val; break;
            case "availability": ret = this.availability.val; break;
            case "latency": ret = this.latency.val; break;
            case "throughput": ret = this.throughput.val; break;

        }
        return ret;
    }
    public object find_upper_val(string str_name)
    {
        object ret = null;
        switch (str_name)
        {
            case "reliability": ret = this.reliability.upper_val; break;
            case "response time": ret = this.responsetime.upper_val; break;
            case "price": ret = this.price.upper_val; break;
            case "authentication": ret = this.authentication.upper_val; break;
            case "availability": ret = this.availability.upper_val; break;
            case "latency": ret = this.latency.upper_val; break;
            case "throughput": ret = this.throughput.upper_val; break;

        }
        return ret;
    }

    public object find_lower_val(string str_name)
    {
        object ret = null;
        switch (str_name)
        {
            case "reliability": ret = this.reliability.lower_val; break;
            case "response time": ret = this.responsetime.lower_val; break;
            case "price": ret = this.price.lower_val; break;
            case "authentication": ret = this.authentication.lower_val; break;
            case "availability": ret = this.availability.lower_val; break;
            case "latency": ret = this.latency.lower_val; break;
            case "throughput": ret = this.throughput.lower_val; break;
        }
        return ret;
    }

    public object find_tendency(string str_name)
    {
        object ret = null;
        switch (str_name)
        {
            case "reliability": ret = this.reliability.tendency; break;
            case "response time": ret = this.responsetime.tendency; break;
            case "price": ret = this.price.tendency; break;
            case "authentication": ret = this.authentication.tendency; break;
            case "availability": ret = this.availability.tendency; break;
            case "latency": ret = this.latency.tendency; break;
            case "throughput": ret = this.throughput.tendency; break;
        }
        return ret;
    }

    public int find_relax_order(string str_name)
    {
        int ret = 0;
        switch (str_name)
        {
            case "reliability": ret = this.reliability.relax_order; break;
            case "response time": ret = this.responsetime.relax_order; break;
            case "price": ret = this.price.relax_order; break;
            case "authentication": ret = this.authentication.relax_order; break;
            case "availability": ret = this.availability.relax_order; break;
            case "latency": ret = this.latency.relax_order; break;
            case "throughput": ret = this.throughput.relax_order; break;
        }
        return ret;
    }

    public int find_pref_order(string str_name)
    {
        int ret = 0;
        switch (str_name)
        {
            case "reliability": ret = this.reliability.pref_Order; break;
            case "response time": ret = this.responsetime.pref_Order; break;
            case "price": ret = this.price.pref_Order; break;
            case "authentication": ret = this.authentication.pref_Order; break;
            case "availability": ret = this.availability.pref_Order; break;
            case "latency": ret = this.latency.pref_Order; break;
            case "throughput": ret = this.throughput.pref_Order; break;
        }
        return ret;
    }


     public object find_type(string str_name)
     {
         object ret = null;
         switch (str_name)
         {
             case "reliability": ret = this.reliability.type; break;
             case "response time": ret = this.responsetime.type; break;
             case "price": ret = this.price.type; break;
             case "authentication": ret = this.authentication.type; break;
             case "availability": ret = this.availability.type; break;
             case "latency": ret = this.latency.type; break;
             case "throughput": ret = this.throughput.type; break;
         }
         return ret;
     }

     public S_Obj(attr_Obj r, attr_Obj rt, attr_Obj p, attr_Obj auth, attr_Obj av , attr_Obj lt , attr_Obj th)
    {
        this.reliability = r;
        this.responsetime = rt;
        this.price = p;
        this.authentication = auth;
        this.availability = av;
        this.latency = lt;
        this.throughput = th;

    }

       public bool isEmpty(S_Obj beChecked) //it's not working correctly
       {
           bool ret = false; 
           if ((beChecked.reliability.val.Equals("0")) && (beChecked.reliability.type.Equals("range")) &&
               (beChecked.reliability.tendency.Equals("positive")) &&
               (beChecked.reliability.pref_Order == 0) && (beChecked.reliability.relax_order == 0) &&
               (beChecked.reliability.upper_val.Equals("0")) && (beChecked.reliability.lower_val.Equals("0"))
               &&
               (beChecked.responsetime.val.Equals("0")) && (beChecked.responsetime.type.Equals("range")) &&
               (beChecked.responsetime.tendency.Equals("positive")) &&
               (beChecked.responsetime.pref_Order == 0) && (beChecked.responsetime.relax_order == 0) &&
               (beChecked.responsetime.upper_val.Equals("0")) && (beChecked.responsetime.lower_val.Equals("0"))
               &&
               (beChecked.price.val.Equals("0")) && (beChecked.price.type.Equals("range")) &&
               (beChecked.price.tendency.Equals("positive")) &&
               (beChecked.price.pref_Order == 0) && (beChecked.price.relax_order == 0) &&
               (beChecked.price.upper_val.Equals("0")) && (beChecked.price.lower_val.Equals("0"))
               &&
               (beChecked.availability.val.Equals("0")) && (beChecked.availability.type.Equals("range")) &&
               (beChecked.availability.tendency.Equals("positive")) &&
               (beChecked.availability.pref_Order == 0) && (beChecked.availability.relax_order == 0) &&
               (beChecked.availability.upper_val.Equals("0")) && (beChecked.availability.lower_val.Equals("0"))
               &&
               (beChecked.latency.val.Equals("0")) && (beChecked.latency.type.Equals("range")) &&
               (beChecked.latency.tendency.Equals("positive")) &&
               (beChecked.latency.pref_Order == 0) && (beChecked.latency.relax_order == 0) &&
               (beChecked.latency.upper_val.Equals("0")) && (beChecked.latency.lower_val.Equals("0"))
               &&
               (beChecked.throughput.val.Equals("0")) && (beChecked.throughput.type.Equals("range")) &&
               (beChecked.throughput.tendency.Equals("positive")) &&
               (beChecked.throughput.pref_Order == 0) && (beChecked.throughput.relax_order == 0) &&
               (beChecked.throughput.upper_val.Equals("0")) && (beChecked.throughput.lower_val.Equals("0"))
               &&
               (beChecked.authentication.val.Equals("0")) && (beChecked.authentication.type.Equals("range")) &&
               (beChecked.authentication.tendency.Equals("positive")) &&
               (beChecked.authentication.pref_Order == 0) && (beChecked.authentication.relax_order == 0) &&
               (beChecked.authentication.upper_val.Equals("0")) && (beChecked.authentication.lower_val.Equals("0")))

               ret = true;
           else
               ret = false;
           return ret;
       }

}
