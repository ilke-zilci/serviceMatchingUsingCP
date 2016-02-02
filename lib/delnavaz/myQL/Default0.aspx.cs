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

public partial class Default0 : System.Web.UI.Page
{
    //protected infoDel my_InfoDel;
    int ind ;

  /*  public enum WizardNavigationTempContainer
    {
        StartNavigationTemplateContainerID = 1,
        StepNavigationTemplateContainerID = 2,
        StepNavigationTemplateContainerID = 3,
        FinishNavigationTemplateContainerID = 4
    }

    private Control GetControlFromWizard(Wizard wizard, WizardNavigationTempContainer wzdTemplate, string controlName)
    {
        System.Text.StringBuilder strCtrl = new System.Text.StringBuilder();
        strCtrl.Append(wzdTemplate);
        strCtrl.Append("$");
        strCtrl.Append(controlName);

        return wizard.FindControl(strCtrl.ToString());
    }
    
    */
    protected void Page_Load(object sender, EventArgs e)
    {
      /*  if (Page.User.Identity.IsAuthenticated == true)
        {
            Button btnBrowse = GetControlFromWizard(wzd, WizardNavigationTempContainer.StartNavigationTemplateContainerID, "btnBrowse") as Button;
            btnBrowse.Enabled = false;
        }
        */    
        
    }
    protected void Wizard1_FinishButtonClick(object sender, WizardNavigationEventArgs e)
    {

    }

  
    protected void chPreferences_CheckedChanged(object sender, EventArgs e)
    {

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
            //RBFuzzyP.Visible = false;
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
            //RBFuzzyP.Visible = true;
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

    protected void btnBrowsing_Click(object sender, EventArgs e)
    {
        GridView1.Visible = true;
    }
    protected void btnReset_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default0.aspx");
    }
    protected void btnAdd_Click(object sender, EventArgs e)
    {
        chbL1.Items.Add(ddlQoS.SelectedItem.Text);

            infoDel my_InfoDel = (infoDel)Page.LoadControl("infoDel.ascx");
            chbL1.Controls.Add(my_InfoDel);
            PlaceHolder1.Controls.Add(my_InfoDel);

            if (PlaceHolder1.Controls.Count == 0)
                ind = 0;
            else
            {
                ind = PlaceHolder1.Controls.Count;
                ind++;
            }
                            
            //PlaceHolder1.Controls.Add(cc[ind]);
              

           // Label my_lblQoSAttr = (Label)my_InfoDel.FindControl("lblQoSAttr");
            //my_lblQoSAttr.Text = chbL1.Items.Count.ToString(); //ddlQoS.SelectedItem.Text;
            
    }
 
    protected void ImageButton2_Click(object sender, ImageClickEventArgs e)
    {

    }
    protected void Wizard1_CancelButtonClick(object sender, EventArgs e)
    {
        Response.Redirect("Default0.aspx");
    }
    protected void RBFuzzyP_SelectedIndexChanged(object sender, EventArgs e)
    {

    }

protected void btnInfo_Click1(object sender, ImageClickEventArgs e)
{
    Response.Write("<script>alert('price is a sum a requester should pay to use a web service. The price is defined by an integer value and descending tendency (the stronger values are the smaller values). e.g, range value: between 100$ and 150$ , or fuzzy value: medium')</script>");
}
protected void chAuthentication_CheckedChanged(object sender, EventArgs e)
{
    if (chAuthentication.Checked)
    {
        RBTsingle.Visible = true;
        ddlSingleAuth.Visible = true;
        AuthPanel.Visible = true;
    }
    else
    {
        RBTsingle.Visible = false;
        ddlSingleAuth.Visible = false;
        AuthPanel.Visible = false;
    }
}
protected void ImageButton9_Click(object sender, ImageClickEventArgs e)
{

}

}
