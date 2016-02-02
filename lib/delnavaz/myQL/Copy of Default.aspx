<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Copy of Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
    <style type="text/css">
        .style1
        {
            width: 80%;
        }
        .style11
        {
            height: 26px;
        }
        .style12
        {
            width: 83%;
        }
        .style13
        {
            width: 536px;
        }
        #form1
        {
            background-color: #CCCCCC;
        }
        .style14
        {
            table-layout: fixed;
            width: 204px;
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
        <table class="style12">
            <tr>
            <td>
                 &nbsp;</td>
            <td>&nbsp;</td>
            </tr>
        
            <tr>
            
                <td align="justify" colspan="0" rowspan="0" valign="top">
                <asp:Image ID="Image1" runat="server" ImageUrl="~/images/boxtop.gif" 
                        Width="354px" />

                    
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    

        <table border="1"              
                        style="border: thick none #FFFFFF; background-image: url('images/boxleftright.gif'); background-repeat: repeat-y; background-color: #C0C0C0;"
                        cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="0" rowspan="0" 
                    style="display: inline; table-layout: fixed; border-collapse: collapse">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    
                            <asp:HyperLink ID="linkBrowse" runat="server" Font-Size="Medium"
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values" BackColor="Silver" 
                        BorderColor="White" BorderStyle="Dotted" BorderWidth="2px">Browsing</asp:HyperLink>

                    

                    <br />
                    <asp:CheckBox ID="chPrice" runat="server" Text="Price" AutoPostBack="True" 
                        oncheckedchanged="chPrice_CheckedChanged" BackColor="Silver" 
                        BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" />
                    <asp:Panel ID="PricePanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" 
                        BackImageUrl="~/images/boxleftright.gif" Visible="False">
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
                            <asp:HyperLink ID="linkBrowsePrice" runat="server" Font-Size="Small" 
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values">Browsing</asp:HyperLink>
                        </asp:Panel>
                        <br />
                        <asp:RadioButton ID="RBPFuzzy" runat="server" AutoPostBack="True" 
                            GroupName="GA" oncheckedchanged="RBPFuzzy_CheckedChanged" Text="Fuzzy value" 
                            Visible="False" />
                        &nbsp;&nbsp;&nbsp;
                        <asp:RadioButtonList ID="RBFuzzyP" runat="server" BorderStyle="Dotted" 
                            BorderWidth="1px" Visible="False" RepeatDirection="Horizontal" 
                            Width="243px" RepeatLayout="Flow">
                            <asp:ListItem>best availabl</asp:ListItem>
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
                        Font-Size="Small" />
                    <asp:Panel ID="AvPanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" 
                        BackImageUrl="~/images/boxleftright.gif" Visible="False">
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
                            <asp:HyperLink ID="linkBrowseA" runat="server" Font-Size="Small" 
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values" visible="False">Browsing</asp:HyperLink>
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
            <tr>
                <td style="display: inline; table-layout: fixed; border-collapse: collapse">
                    <br />
                    <asp:CheckBox ID="chResponseTime" runat="server" Text="Response Time" 
                        AutoPostBack="True" oncheckedchanged="chResponseTime_CheckedChanged" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" />                    
                    <asp:Panel ID="RTPanel" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                        Font-Size="Small" Width="352px" BackColor="Silver" 
                        BackImageUrl="~/images/boxleftright.gif" Visible="False">
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
                            <asp:HyperLink ID="linkBrowseRT" runat="server" Font-Size="Small" 
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values" visible="False">Browsing</asp:HyperLink>
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
                        Font-Size="Small" Width="352px" Visible="False">
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
                            <asp:HyperLink ID="linkBrowseR" runat="server" Font-Size="Small" 
                                href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" 
                                title="Browse the possible values" visible="False">Browsing</asp:HyperLink>
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
                    <asp:Image ID="Image2" runat="server" ImageUrl="~/images/boxbottom.gif" 
                        Width="354px" />

                </td>
            </tr>
        </table>
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
                            <table align="center" border="0">
                                <tr>
                                    <td bgcolor="#CCCCCC">
                                        <asp:Panel ID="PanelPref" runat="server">
                                            <br />
                                            <br />
                                        </asp:Panel>
                                    </td>
                                    <td align="center">
                                        <asp:Label ID="lblWeight" runat="server" Text="weight"></asp:Label>
                                    </td>
                                    <td align="center">
                                        <asp:Label ID="lblPriority" runat="server" Text="Priority"></asp:Label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style11">
                                        <asp:CheckBox ID="chPricePref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Price" />
                                    </td>
                                    <td class="style11">
                                        <asp:TextBox ID="TextBox1" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                    <td class="style11">
                                        <asp:TextBox ID="TextBox5" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chAvailabilityPref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Availability" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox2" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox6" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <asp:CheckBox ID="chResponseTimePref" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Response Time" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox3" runat="server" Width="75px"></asp:TextBox>
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
                                        <asp:TextBox ID="TextBox4" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox8" runat="server" Width="75px"></asp:TextBox>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <br />
                        </asp:Panel>
                    </td>
            </tr>
            <tr>
                <td style="display: inline; table-layout: fixed; border-collapse: collapse" 
                    colspan="2" valign="top">
                       <asp:CheckBox ID="chRelaxationSet" runat="server" AutoPostBack="True" 
                           oncheckedchanged="chAvailability_CheckedChanged" Text="Relaxation Order" 
                           Font-Size="Small" BackColor="Silver" BorderColor="Black" 
                           BorderStyle="Dotted" BorderWidth="1px" />
                        <asp:Panel ID="Panel18" runat="server" Font-Size="Small" BorderStyle="Dotted" 
                            BorderWidth="1px">
                            <table class="style1" align="center" border="0" cellpadding="0" 
                                cellspacing="0">
                                <tr>
                                    <td bgcolor="#CCCCCC">
                                        <asp:Panel ID="PanelPref0" runat="server">
                                            <br />
                                            <br />
                                        </asp:Panel>
                                    </td>
                                    <td align="center">
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
                                        <asp:CheckBox ID="chAvailabilityPref0" runat="server" AutoPostBack="True" 
                                            oncheckedchanged="chAvailability_CheckedChanged" Text="Availability" />
                                    </td>
                                    <td>
                                        <asp:TextBox ID="TextBox15" runat="server" Width="100px"></asp:TextBox>
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
            <td valign="top">
                <asp:CheckBox ID="CheckBox5" runat="server" Font-Size="Small" Text="Time" 
                    BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" />
                <asp:Panel ID="Panel19" runat="server" BorderStyle="Dotted" BorderWidth="1px">
                    
                    <table id="tblTime">
                        <tr>
                            <td width="50%">
                                <asp:CheckBox ID="CheckBox1" runat="server" Text="Start Date" 
                                    Font-Size="Small" />
                                <asp:TextBox ID="TextBox20" runat="server" Width="100px" Font-Size="Small"></asp:TextBox>
                                    
                                    <asp:Calendar ID="Calendar3" runat="server" BackColor="White" 
                        BorderColor="#999999" CellPadding="0" DayNameFormat="Shortest" 
                        Font-Names="Verdana" Font-Size="8pt" ForeColor="#333333" Height="94px" 
                        ShowGridLines="True" Width="144px">
                                        <SelectedDayStyle BackColor="#666666" Font-Bold="True" ForeColor="White" />
                                        <SelectorStyle BackColor="#CCCCCC" />
                                        <WeekendDayStyle BackColor="#FFFFCC" />
                                        <TodayDayStyle BackColor="#CCCCCC" ForeColor="Black" />
                                        <OtherMonthDayStyle ForeColor="#808080" />
                                        <NextPrevStyle VerticalAlign="Bottom" />
                                        <DayHeaderStyle BackColor="#CCCCCC" Font-Bold="True" Font-Size="7pt" />
                                        <TitleStyle BackColor="#999999" BorderColor="Black" Font-Bold="True" />
                    </asp:Calendar>                            
                            </td>
                            <td width="50%">
                                <asp:CheckBox ID="CheckBox2" runat="server" Text="End Date" Font-Size="Small" />
                                <asp:TextBox ID="TextBox12" runat="server" Width="100px" Font-Size="Small"></asp:TextBox>
                                    <asp:Calendar ID="Calendar1" runat="server" BackColor="White" 
                        BorderColor="#999999" CellPadding="0" DayNameFormat="Shortest" 
                        Font-Names="Verdana" Font-Size="8pt" ForeColor="#333333" Height="94px" 
                        ShowGridLines="True" Width="144px">
                                        <SelectedDayStyle BackColor="#666666" Font-Bold="True" ForeColor="White" />
                                        <SelectorStyle BackColor="#CCCCCC" />
                                        <WeekendDayStyle BackColor="#FFFFCC" />
                                        <TodayDayStyle BackColor="#CCCCCC" ForeColor="Black" />
                                        <OtherMonthDayStyle ForeColor="#808080" />
                                        <NextPrevStyle VerticalAlign="Bottom" />
                                        <DayHeaderStyle BackColor="#CCCCCC" Font-Bold="True" Font-Size="7pt" />
                                        <TitleStyle BackColor="#999999" BorderColor="Black" Font-Bold="True" />
                    </asp:Calendar>                                                    
                            </td>
            </tr>    
            <tr>
                <td colspan="2">
                                <asp:CheckBox ID="CheckBox3" runat="server" 
                        Text="Duration of useage" Font-Size="Small" />
                                &nbsp;
                    <asp:DropDownList ID="DropDownList3" runat="server" Font-Size="Small">
                        <asp:ListItem>1</asp:ListItem>
                        <asp:ListItem>2</asp:ListItem>
                        <asp:ListItem>3</asp:ListItem>
                        <asp:ListItem>4</asp:ListItem>
                        <asp:ListItem>5</asp:ListItem>
                        <asp:ListItem>6</asp:ListItem>
                        <asp:ListItem>7</asp:ListItem>
                        <asp:ListItem>8</asp:ListItem>
                        <asp:ListItem>9</asp:ListItem>
                        <asp:ListItem>10</asp:ListItem>
                    </asp:DropDownList>
&nbsp;&nbsp;
                    <asp:DropDownList ID="DropDownList4" runat="server" Font-Size="Small">
                        <asp:ListItem>hour</asp:ListItem>
                        <asp:ListItem></asp:ListItem>
                    </asp:DropDownList>                
                </td>
            </tr>                    
            <tr>
                <td colspan="2">

                                <asp:CheckBox ID="CheckBox4" runat="server" 
                        Text="Frequency of useage" Font-Size="Small" />
                                &nbsp;<asp:DropDownList ID="DropDownList1" runat="server" 
                        Font-Size="Small">
                        <asp:ListItem>1</asp:ListItem>
                        <asp:ListItem>2</asp:ListItem>
                        <asp:ListItem>3</asp:ListItem>
                        <asp:ListItem>4</asp:ListItem>
                        <asp:ListItem>5</asp:ListItem>
                        <asp:ListItem>6</asp:ListItem>
                        <asp:ListItem>7</asp:ListItem>
                        <asp:ListItem>8</asp:ListItem>
                        <asp:ListItem>9</asp:ListItem>
                        <asp:ListItem>10</asp:ListItem>
                    </asp:DropDownList>
&nbsp;<asp:Label ID="Label3" runat="server" Font-Size="Small" Text="times per"></asp:Label>
&nbsp;<asp:DropDownList ID="DropDownList2" runat="server" Font-Size="Small">
                        <asp:ListItem>day</asp:ListItem>
                        <asp:ListItem>week</asp:ListItem>
                        <asp:ListItem>month</asp:ListItem>
                        <asp:ListItem>year</asp:ListItem>
                    </asp:DropDownList>                
                </td>                        
                        </tr>                    
                    </table>
                </asp:Panel>
            </td>

            </tr>                                
        </table>
                                      

                    
                    


                    
                </td>
            </tr>
            <tr>
                <td class="style13">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
        </table>
        <br />
        <asp:MultiView ID="MultiView1" runat="server" ActiveViewIndex="0">
            <asp:View ID="View1" runat="server">
                <asp:Button ID="btnRequest" runat="server" Text="Request" />
            </asp:View>
        </asp:MultiView>
    </div>
    
    </form>
</body>
</html>
