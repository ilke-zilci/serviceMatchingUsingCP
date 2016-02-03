using System;
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

public partial class _Default : System.Web.UI.Page 
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void RBA_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
    protected void chAvailability_CheckedChanged(object sender, EventArgs e)
    {
        if (chAvailability.Checked)
        {
            AvPanel.Visible = true;
            RBASingle.Visible = true;
            RBARange.Visible = true;
            RBAFuzzy.Visible = true;
        }
        else
        {
            AvPanel.Visible = false;
            RBASingle.Visible = false;
            RBARange.Visible = false;
            RBAFuzzy.Visible = false;
        }
    }
    protected void RBAFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBAFuzzy.Checked)
        {
            txtSingleA.Visible = false;
            txtRangeFromA.Visible = false;
            txtRangeToA.Visible = false;
            RBFuzzyA.Visible = true;
            PRange.Visible = false;
            lblfrom1.Visible = false;
            lblto1.Visible = false;
        }

    }
    protected void RBARange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBARange.Checked)
        {
            txtSingleA.Visible = false;
            txtRangeFromA.Visible = true;
            txtRangeToA.Visible = true;
            RBFuzzyA.Visible = false;
            PRange.Visible = true;
            lblfrom1.Visible = true;
            lblto1.Visible = true;
        }

    }
    protected void RBASingle_CheckedChanged(object sender, EventArgs e)
    {
        if (RBASingle.Checked)
        {
            txtSingleA.Visible = true;
            txtRangeFromA.Visible = false;
            txtRangeToA.Visible = false;
            RBFuzzyA.Visible = false;
            PRange.Visible = false;
            lblfrom1.Visible = false;
            lblto1.Visible = false;
        }

    }
    protected void chPreferences_CheckedChanged(object sender, EventArgs e)
    {

    }
    protected void chPrice_CheckedChanged(object sender, EventArgs e)
    {
        if (chPrice.Checked)
        {
            PricePanel.Visible = true;
            RBPSingle.Visible = true;
            RBPRange.Visible = true;
            RBPFuzzy.Visible = true;
        }
        else
        {
            PricePanel.Visible = false;
            RBPSingle.Visible = false;
            RBPRange.Visible = false;
            RBPFuzzy.Visible = false;
        }
    }
    protected void RBPSingle_CheckedChanged(object sender, EventArgs e)
    {
        if (RBPSingle.Checked)
        {
            txtSingleP.Visible = true;
            txtRangeFromP.Visible = false;
            txtRangeToP.Visible = false;
            RBFuzzyP.Visible = false;
            //RBPFuzzy.Visible = false;
            PRangePrice.Visible = false;
            lblfromP.Visible = false;
            lbltoP.Visible = false;
        }
        
    }
    protected void RBPRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBPRange.Checked)
        {
            txtSingleP.Visible = false;
            txtRangeFromP.Visible = true;
            txtRangeToP.Visible = true;
            RBFuzzyP.Visible = false;
            PRangePrice.Visible = true;
            lblfromP.Visible = true;
            lbltoP.Visible = true;
        }
    }
    protected void RBPFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBPFuzzy.Checked)
        {
            txtSingleP.Visible = false;
            txtRangeFromP.Visible = false;
            txtRangeToP.Visible = false;
            RBFuzzyP.Visible = true;
            PRangePrice.Visible = false;
            lblfromP.Visible = false;
            lbltoP.Visible = false;
        }
    }
    protected void chReliability_CheckedChanged(object sender, EventArgs e)
    {
        if (chReliability.Checked)
        {
            RPanel.Visible = true;
            RBRSingle.Visible = true;
            RBRRange.Visible = true;
            RBRFuzzy.Visible = true;
        }
        else
        {
            RPanel.Visible = false;
            RBRSingle.Visible = false;
            RBRRange.Visible = false;
            RBRFuzzy.Visible = false;
        }
    }
    protected void chResponseTime_CheckedChanged(object sender, EventArgs e)
    {
        if (chResponseTime.Checked)
        {
            RTPanel.Visible = true;
            RBRTSingle.Visible = true;
            RBRTRange.Visible = true;
            RBRTFuzzy.Visible = true;
        }
        else
        {
            RTPanel.Visible = false;
            RBRTSingle.Visible = false;
            RBRTRange.Visible = false;
            RBRTFuzzy.Visible = false;
        }
    }
    protected void RBRTSingle_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRTSingle.Checked)
        {
            txtSingleRT.Visible = true;
            txtRangeFromRT.Visible = false;
            txtRangeToRT.Visible = false;
            RBFuzzyRT.Visible = false;
            PRangeRT.Visible = false;
            lblfromRT.Visible = false;
            lbltoRT.Visible = false;
         }
    }
    protected void RBRTRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRTRange.Checked)
        {
            txtSingleRT.Visible = false;
            txtRangeFromRT.Visible = true;
            txtRangeToRT.Visible = true;
            RBFuzzyRT.Visible = false;
            PRangeRT.Visible = true;
            lblfromRT.Visible = true;
            lbltoRT.Visible = true;

        }

    }
    protected void RBRTFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRTFuzzy.Checked)
        {
            txtSingleRT.Visible = false;
            txtRangeFromRT.Visible = false;
            txtRangeToRT.Visible = false;
            RBFuzzyRT.Visible = true;
            PRangeRT.Visible = false;
            lblfromRT.Visible = false;
            lbltoRT.Visible = false;

        }
    }
    protected void RBRRange_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRSingle.Checked)
        {
            txtSingleR.Visible = true;
            txtRangeFromR.Visible = false;
            txtRangeToR.Visible = false;
            RBFuzzyR.Visible = false;
            PRangeR.Visible = false;
            lblfromR.Visible = false;
            lbltoR.Visible = false;

        }
    }
    protected void RBRSingle_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRSingle.Checked)
        {
            txtSingleR.Visible = true;
            txtRangeFromR.Visible = false;
            txtRangeToR.Visible = false;
            RBFuzzyR.Visible = false;
            PRangeR.Visible = false;
            lblfromR.Visible = false;
            lbltoR.Visible = false;

        }
    }
    protected void RBRFuzzy_CheckedChanged(object sender, EventArgs e)
    {
        if (RBRFuzzy.Checked)
        {
            txtSingleR.Visible = false;
            txtRangeFromR.Visible = false;
            txtRangeToR.Visible = false;
            RBFuzzyR.Visible = true;
            PRangeR.Visible = false;
            lblfromR.Visible = false;
            lbltoR.Visible = false;
        }
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default2.aspx");
    }
    protected void btnReset_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default.aspx");
    }
}
