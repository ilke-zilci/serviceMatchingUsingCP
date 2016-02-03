<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
    <style type="text/css">
        .style1
        {
            width: 92%;
        }
        .style11
        {
            height: 26px;
        }
        .style12
        {
            width: 73%;
        }
        #form1
        {
            background-color: #CCCCCC;
        }
        .style14
        {
            table-layout: fixed;
            width: 377px;
        }
        .style15
        {
            width: 414px;
        }
        .style16
        {
            width: 377px;
        }
        </style>
    <script language="javascript" type="text/javascript">

            function BrowsingPage(strField)
            {
                        window.open('Browsing.aspx', 'BrowsingPopup', 'width=250,height=190,resizable=yes'); //;?field=' + strField, 'calendarPopup', 'width=250,height=190,resizable=yes');
            }

</script>

</head>
<body bgcolor="#CCCCCC">
    <form id="form1" runat="server">
    <div>
        <br />
        <table class="style12" align="center" border="0" width="60%">
            <tr>
            <td class="style15">
                 &nbsp;</td>
            <td>&nbsp;</td>
            </tr>
        
            <tr>
            
                <td align="justify" colspan="0" rowspan="0" valign="top" class="style15">
                    <asp:Label ID="Label5" runat="server" BackColor="White" 
                        Text="QoS Attributes' Constraints"></asp:Label>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    

        <table border="1"              
                        style="border: thick groove #FFFFFF; table-layout: auto;"
                        cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="0" rowspan="0" 
                    style="display: inline; table-layout: fixed; border-collapse: collapse">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    
                            <asp:HyperLink ID="linkBrowse" runat="server" Font-Size="Medium"
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values" BackColor="Silver" 
                        BorderColor="White" BorderStyle="Dotted" BorderWidth="1px">Browsing</asp:HyperLink>

                    

                    <br />
                    <asp:CheckBox ID="chPrice" runat="server" Text="Price" AutoPostBack="True" 
                        oncheckedchanged="chPrice_CheckedChanged" BackColor="Silver" 
                        BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" />
                    <asp:Panel ID="PricePanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" Visible="False">
                        <asp:RadioButton ID="RBPSingle" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBPSingle_CheckedChanged" Text="Single value" 
                            Visible="False" />
                        &nbsp;
                        <asp:TextBox ID="txtSingleP" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        
                        <br />
                        
                        <asp:RadioButton ID="RBPRange" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBPRange_CheckedChanged" Text="Range value" 
                            Visible="False" />
                        <asp:Panel ID="PRangePrice" runat="server" BorderStyle="Dotted" BorderWidth="1px" 
                            Visible="False" Width="98%">
                            &nbsp;&nbsp;
                            <asp:Label ID="lblfromP" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;
                            <asp:TextBox ID="txtRangeFromP" runat="server" Visible="False" Width="95px"></asp:TextBox>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="lbltoP" runat="server" Text="to:" Visible="False"></asp:Label>
                            &nbsp;
                            <asp:TextBox ID="txtRangeToP" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        </asp:Panel>
                        <br />
                        <asp:RadioButton ID="RBPFuzzy" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBPFuzzy_CheckedChanged" Text="Fuzzy value" 
                            Visible="False" />
                        &nbsp;&nbsp;&nbsp;
                        <asp:RadioButtonList ID="RBFuzzyP" runat="server" BorderStyle="Dotted" 
                            BorderWidth="1px" Visible="False" RepeatDirection="Horizontal" 
                            Width="251px" RepeatLayout="Flow" Height="18px">
                            <asp:ListItem>good</asp:ListItem>
                            <asp:ListItem>best availabl</asp:ListItem>
                            <asp:ListItem>poor</asp:ListItem>
                            <asp:ListItem>medium</asp:ListItem>
                        </asp:RadioButtonList>
                    </asp:Panel>                
                </td>
                </tr>
            <tr>
                <td style="display: inline; table-layout: fixed; border-collapse: collapse">
                    <br />
                    <asp:CheckBox ID="chResponseTime" runat="server" Text="Response Time" 
                        AutoPostBack="True" oncheckedchanged="chResponseTime_CheckedChanged" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" />                    
                    <asp:Panel ID="RTPanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" Visible="False">
                        <asp:RadioButton ID="RBRTSingle" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRTSingle_CheckedChanged" Text="Single value" 
                            Visible="False" />
                        &nbsp;
                        <asp:TextBox ID="txtSingleRT" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        <br />
                        <asp:RadioButton ID="RBRTRange" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRTRange_CheckedChanged" Text="Range value" 
                            Visible="False" />
&nbsp;
                        <br />
                        <asp:Panel ID="PRangeRT" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                            Visible="False" Width="98%">
                            &nbsp;&nbsp;
                            <asp:Label ID="lblfromRT" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;
                            <asp:TextBox ID="txtRangeFromRT" runat="server" Visible="False" Width="95px"></asp:TextBox>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="lbltoRT" runat="server" Text="to:" Visible="False"></asp:Label>
                            &nbsp;
                            <asp:TextBox ID="txtRangeToRT" runat="server" Visible="False" Width="95px" ></asp:TextBox>
                        </asp:Panel>
                        <br />
                        <asp:RadioButton ID="RBRTFuzzy" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRTFuzzy_CheckedChanged" Text="Fuzzy value" 
                            Visible="False" />
                        &nbsp;&nbsp;&nbsp;
                        <asp:RadioButtonList ID="RBFuzzyRT" runat="server" BorderStyle="Groove" 
                            BorderWidth="0px" Visible="False" RepeatDirection="Horizontal" 
                            RepeatLayout="Flow">
                            <asp:ListItem>best available</asp:ListItem>
                            <asp:ListItem>good</asp:ListItem>
                            <asp:ListItem>medium</asp:ListItem>
                            <asp:ListItem>poor</asp:ListItem>
                        </asp:RadioButtonList>
                    </asp:Panel>
                </td>
                </tr>
                <tr>
                <td style="display: inline; table-layout: fixed; border-collapse: collapse">
                    <br />
                    <asp:CheckBox ID="chReliability" runat="server" Text="Reliability" 
                        AutoPostBack="True" oncheckedchanged="chReliability_CheckedChanged" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" />
                    <asp:Panel ID="RPanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" Visible="False" BackColor="Silver">
                        <asp:RadioButton ID="RBRSingle" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRSingle_CheckedChanged" Text="Single value" 
                            Visible="False" />
                        &nbsp;
                        <asp:TextBox ID="txtSingleR" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        <br />
                        <asp:RadioButton ID="RBRRange" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRRange_CheckedChanged" Text="Range value" 
                            Visible="False" />
&nbsp;
                        <br />
                        <asp:Panel ID="PRangeR" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                            Visible="False" Width="98%">
                            &nbsp;&nbsp;
                            <asp:Label ID="lblfromR" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;
                            <asp:TextBox ID="txtRangeFromR" runat="server" Visible="False" Width="95px"></asp:TextBox>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="lbltoR" runat="server" Text="to:" Visible="False"></asp:Label>
                            &nbsp;
                            <asp:TextBox ID="txtRangeToR" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        </asp:Panel>
                        <br />
                        <asp:RadioButton ID="RBRFuzzy" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBRFuzzy_CheckedChanged" Text="Fuzzy value" 
                            Visible="False" />
                        &nbsp;&nbsp;&nbsp;
                        <asp:RadioButtonList ID="RBFuzzyR" runat="server" BorderStyle="Groove" 
                            BorderWidth="0px" RepeatDirection="Horizontal" Visible="False" 
                            RepeatLayout="Flow">
                            <asp:ListItem>best available</asp:ListItem>
                            <asp:ListItem>good</asp:ListItem>
                            <asp:ListItem>medium</asp:ListItem>
                            <asp:ListItem>poor</asp:ListItem>
                        </asp:RadioButtonList>
                    </asp:Panel>

                </td>
            </tr>
                <tr>
                <td colspan="0" rowspan="0" style="display: inline; table-layout: fixed; border-collapse: collapse">
                    <br />
                    <asp:CheckBox ID="chAvailability" runat="server" Text="Availability" 
                        AutoPostBack="True" oncheckedchanged="chAvailability_CheckedChanged" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" Visible="False" />
                    <asp:Panel ID="AvPanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" Visible="False">
                        <asp:RadioButton ID="RBASingle" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBASingle_CheckedChanged" Text="Single value" 
                            Visible="False" />
                        &nbsp;
                        <asp:TextBox ID="txtSingleA" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        <br />
                        <asp:RadioButton ID="RBARange" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBARange_CheckedChanged" Text="Range value" 
                            Visible="False" />
&nbsp;
                        <br />
                        <asp:Panel ID="PRange" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                            Visible="False" Width="98%">
                            &nbsp;&nbsp;
                            <asp:Label ID="lblfrom1" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;
                            <asp:TextBox ID="txtRangeFromA" runat="server" Visible="False" Width="95px"></asp:TextBox>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <asp:Label ID="lblto1" runat="server" Text="to:" Visible="False"></asp:Label>
                            &nbsp;
                            <asp:TextBox ID="txtRangeToA" runat="server" Visible="False" Width="95px"></asp:TextBox>
                        </asp:Panel>
                        <br />
                        <asp:RadioButton ID="RBAFuzzy" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBAFuzzy_CheckedChanged" Text="Fuzzy value" 
                            Visible="False" />
                        &nbsp;&nbsp;&nbsp;
                        <asp:RadioButtonList ID="RBFuzzyA" runat="server" BorderStyle="Groove" 
                            BorderWidth="0px" Visible="False" RepeatDirection="Horizontal" 
                            RepeatLayout="Flow">
                            <asp:ListItem>best available</asp:ListItem>
                            <asp:ListItem>good</asp:ListItem>
                            <asp:ListItem>medium</asp:ListItem>
                            <asp:ListItem>poor</asp:ListItem>
                        </asp:RadioButtonList>
                    </asp:Panel>
                </td>
            </tr>
            
        </table>
        
                <asp:Button ID="btnRequest" runat="server" Text="Request" Visible="False" />
                <asp:Button ID="btnReset" runat="server" Text="Reset" onclick="btnReset_Click" />
                <asp:Button ID="btnNext" runat="server" Text="Next" onclick="btnNext_Click" />
        
                </td>
                <td>
                    <asp:Label ID="Label4" runat="server" BackColor="White" 
                        Text="Request's Features"></asp:Label>

        <table class="style1" border="1" style="border: thick groove #FFFFFF; table-layout: auto;">
            <tr>
                <td colspan="2" style="display: inline; border-collapse: collapse" 
                    class="style14" valign="top">
                            <asp:CheckBox ID="chPreferences" runat="server" AutoPostBack="True" 
                                oncheckedchanged="chPreferences_CheckedChanged" Text="Preferences" 
                                Font-Size="Small" BackColor="Silver" BorderColor="Black" 
                                BorderStyle="Dotted" BorderWidth="1px" />
                        <asp:Panel ID="Panel17" runat="server" Font-Size="Small" BorderStyle="Dotted" 
                            BorderWidth="1px">
                            <table align="center" border="0" class="style11" width="70%">
                                <tr>
                                    <td bgcolor="#CCCCCC">
                                        <asp:Panel ID="PanelPref" runat="server">
                                            <br />
                                            <br />
                                        </asp:Panel>
                                    </td>
                                    <td align="left">
                                        <asp:Label ID="lblPriority" runat="server" Text="Priority"></asp:Label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style11">
                                        <asp:CheckBox ID="chPricePref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Price" />
                                    </td>
                                    <td class="style11">
                                        <asp:TextBox ID="TextBox5" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chResponseTimePref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Response Time" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox7" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chReliabilityPref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Reliability" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox8" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr visible="false">
                                    <td>
                                        &nbsp;</td>
                                    <td>
                                        &nbsp;</td>
                                </tr>
                                
                            </table>
                            <br />
                            <br />
                        </asp:Panel>
                    </td>
            </tr>
            <tr>
                <td style="display: inline; border-collapse: collapse" 
                    colspan="2" valign="top" class="style16">
                       <asp:CheckBox ID="chRelaxationSet" runat="server" AutoPostBack="True" 
                           oncheckedchanged="chAvailability_CheckedChanged" Text="Relaxation Order" 
                           Font-Size="Small" BackColor="Silver" BorderColor="Black" 
                           BorderStyle="Dotted" BorderWidth="1px" />
                        <asp:Panel ID="Panel18" runat="server" Font-Size="Small" BorderStyle="Dotted" 
                            BorderWidth="1px">
                            <table class="style11" align="center" border="0" cellpadding="0" 
                                cellspacing="0" width="70%">
                                <tr>
                                    <td bgcolor="#CCCCCC">
                                        <asp:Panel ID="PanelPref0" runat="server">
                                            <br />
                                            <br />
                                        </asp:Panel>
                                    </td>
                                    <td align="left">
                                        <asp:Label ID="lblPriority0" runat="server" Text="Priority"></asp:Label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style11">
                                        <asp:CheckBox ID="chPrice1" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Price" />
                                    </td>
                                    <td class="style11">
                                        <asp:TextBox ID="TextBox13" runat="server" Width="100px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chResponseTime1" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Response Time" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox17" runat="server" Width="100px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chReliability1" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Reliability" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox19" runat="server" Width="100px"></asp:TextBox>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <br />
                        </asp:Panel>
                    </td>
            </tr>
            <tr>
            <td valign="top" class="style16">
                &nbsp;</td>

            </tr>                                
        </table>
                                      

                    
                    


                    
                </td>
            </tr>
            <tr>
                <td class="style15">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
        </table>
        <br />
        <asp:MultiView ID="MultiView1" runat="server" ActiveViewIndex="0">
            <asp:View ID="View1" runat="server">
            </asp:View>
        </asp:MultiView>
    </div>
    
    </form>
</body>
</html>
