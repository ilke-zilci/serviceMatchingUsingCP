<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default2.aspx.cs" Inherits="Default2" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
    <style type="text/css">

        #form1
        {
            background-color: #CCCCCC;
        }
        .style12
        {
            width: 73%;
        }
        .style15
        {
            width: 541px;
        }
        #tblTime
        {
            width: 549px;
        }
        .style16
        {
            table-layout: fixed;
            width: 521px;
        }
    </style>
</head>
<body bgcolor="#CCCCCC">
    <form id="form1" runat="server">
    <div>
        <br />
        <table class="style12" align="center" border="0" width="60%">
            <tr>
            <td class="style15">
                 &nbsp;</td>
            </tr>
        
            <tr>
            
                <td align="justify" colspan="0" rowspan="0" valign="top" class="style15">
                <asp:CheckBox ID="CheckBox5" runat="server" Font-Size="Small" Text="Time" 
                    BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Visible="False" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    

        <table border="1"              
                        style="border: thick groove #FFFFFF; table-layout: auto; width: 557px;"
                        cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="0" rowspan="0" 
                    style="display: inline; border-collapse: collapse" class="style16">
                <asp:Panel ID="Panel19" runat="server" BorderStyle="Dotted" BorderWidth="0px">
                    
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
                                <br />
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

                                <br />

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
        
                <asp:Button ID="btnRequest" runat="server" Text="Request" Visible="False" />
                <asp:Button ID="btnReset" runat="server" Text="Reset" onclick="btnReset_Click" />
                <asp:Button ID="btnBack" runat="server" Text="Back" onclick="btnBack_Click" />
                <asp:Button ID="btnNext" runat="server" Text="Next" />
        
                </td>
            </tr>
            <tr>
                <td class="style15">
                    &nbsp;</td>
            </tr>
        </table>
        <br />
    </div>
    
    </form>
</body>
</html>
