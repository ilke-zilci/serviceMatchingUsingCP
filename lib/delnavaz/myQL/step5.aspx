<%@ Page Language="C#" AutoEventWireup="true" CodeFile="step5.aspx.cs" Inherits="step5" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
          <table border="1" cellpadding="0" cellspacing="0" style="border: medium solid #C0C0C0; " align="center">
            <tr>
                 <td colspan="2" align="center" bgcolor="#003366" style="font-size: small; color: #FFFFFF; font-weight: bolder;" >Step 5 - define time constraints</td>
            </tr> 
                        <tr align="left">
                            <td align="left">
                                <asp:ScriptManager runat="server" ID="ScriptManager1">
                                </asp:ScriptManager>
                            </td>
                        </tr>
                        <tr>
                            <td align="justify" colspan="0" rowspan="0" valign="top" >
                                <asp:CheckBox runat="server" Text="Time" BorderColor="Black" BorderWidth="1px" 
                                    BorderStyle="Dotted" Font-Size="Small" ID="CheckBox5"></asp:CheckBox>
                                &nbsp;<asp:ImageButton runat="server" ImageUrl="~/images/info-icon-preview.jpg" Height="20px" Width="20px" ID="ImageButton7">
                                </asp:ImageButton>
                                &nbsp;<table border="1" cellpadding="0" cellspacing="0" 
                                    style="border: thick groove #FFFFFF; table-layout: auto; " width="70%">
                                    <tr>
                                        <td colspan="0" rowspan="0" 
                                            style="display: inline; border-collapse: collapse">
                                            <asp:Panel runat="server" BorderWidth="0px" ID="Panel19">
                                                <table ID="tblTime" align="center" width="470px">
                                                    <tr>
                                                        <td width="50%">
                                                            <asp:CheckBox runat="server" Text="Start Date" Font-Size="Small" ID="CheckBox1">
                                                            </asp:CheckBox>
                                                            <asp:TextBox runat="server" Font-Size="X-Small" Width="95px" ID="TextBox20">[selected_date]</asp:TextBox>
                                                            <cc1:CalendarExtender runat="server" Format="MM/dd/yyyy" PopupButtonID="cal1" 
                                                                Enabled="True" TargetControlID="TextBox20" ID="TextBox20_CalendarExtender0">
                                                            </cc1:CalendarExtender>
                                                            <asp:ImageButton runat="server" ImageUrl="~/images/cal8.jpg" Width="20px" ID="cal1">
                                                            </asp:ImageButton>
                                                        </td>
                                                        <td width="50%">
                                                            <asp:CheckBox runat="server" Text="End Date" Font-Size="Small" ID="CheckBox2">
                                                            </asp:CheckBox>
                                                            <asp:TextBox runat="server" Font-Size="X-Small" Width="95px" ID="TextBox12">[selected_date]</asp:TextBox>
                                                            <cc1:CalendarExtender runat="server" PopupButtonID="cal2" Enabled="True" 
                                                                TargetControlID="TextBox12" ID="TextBox12_CalendarExtender0">
                                                            </cc1:CalendarExtender>
                                                            <asp:ImageButton runat="server" ImageUrl="~/images/cal8.jpg" Width="20px" ID="cal2">
                                                            </asp:ImageButton>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <br />
                                                            <asp:CheckBox runat="server" Text="Duration of usage" Font-Size="Small" ID="CheckBox3">
                                                            </asp:CheckBox>
                                                            &nbsp;
                                                            <asp:DropDownList runat="server" Font-Size="Small" ID="DropDownList3">
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
                                                            <asp:DropDownList runat="server" Font-Size="Small" ID="DropDownList4">
                                                                <asp:ListItem>hours</asp:ListItem>
                                                                <asp:ListItem></asp:ListItem>
                                                            </asp:DropDownList>
                                                            &nbsp;<asp:Label runat="server" Text="per invocation" Font-Size="Small" ID="Label5"></asp:Label>
                                                            &nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <br />
                                                            <asp:CheckBox runat="server" Text="Frequency of usage" Font-Size="Small" ID="CheckBox4">
                                                            </asp:CheckBox>
                                                            &nbsp;<asp:DropDownList runat="server" Font-Size="Small" ID="DropDownList1">
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
                                                            &nbsp;<asp:Label runat="server" Text="times per" Font-Size="Small" ID="Label3"></asp:Label>
                                                            &nbsp;<asp:DropDownList runat="server" Font-Size="Small" ID="DropDownList2">
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
                            <td >
                                &nbsp;</td>
                        </tr>
                                    <tr >
                <td colspan="2" bgcolor="#003366">
                    <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" 
                        onclick="btnCancel_Click" /><asp:Button ID="btnPrevious" runat="server" Text="Previous" 
                        CausesValidation="false" nclick="btnNext_Click" 
                        onclick="btnPrevious_Click" />
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                     
                     <asp:Button ID="btnNext" runat="server" Text="submit" CausesValidation="false" 
                        nclick="btnNext_Click" onclick="btnNext_Click" />
               </td>
            </tr> 
                    </table>
    <div>
    
    </div>
    </form>
</body>
</html>
