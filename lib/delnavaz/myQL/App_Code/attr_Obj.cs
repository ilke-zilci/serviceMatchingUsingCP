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
/// Summary description for attr_Obj
/// </summary>
public class attr_Obj
{
    public string type;
    public int relax_order;
    public int pref_Order;
    public object val;
    public object upper_val;
    public object lower_val;
    public object tendency;


    public attr_Obj(string sType, int r_o, int p_O, object v, object l_v, object u_v, string tend)
	{
        this.type = sType;
        this.relax_order = r_o;
        this.pref_Order = p_O;
        this.val = v;
        this.lower_val = l_v;
        this.upper_val = u_v;
        this.tendency = tend;
 	}


}
