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

public partial class infoDel : System.Web.UI.UserControl
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void btnInfo_Click(object sender, ImageClickEventArgs e)
    {
        lblInfo.Text = "definition + an example"; 
    }
    protected void btnDel_Click(object sender, ImageClickEventArgs e)
    {
        lblQoSAttr.Text = "";
    }
}
