<%@ Page Language="C#" AutoEventWireup="true" CodeFile="step4.aspx.cs" Inherits="step4" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <table border="1" cellpadding="0" cellspacing="0" style="border: medium solid #C0C0C0; " align="center">
            <tr>
                 <td colspan="2" align="center" bgcolor="#003366" style="font-size: small; color: #FFFFFF; font-weight: bolder;" >Step 4 - preferences and relaxations</td>
            </tr>   
            <tr>
                <td class="style1" style="display: inline; border-collapse: collapse" 
                    valign="top">
                    <asp:CheckBox ID="chPreferences" runat="server" AutoPostBack="True" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" 
                        Text="Order of Preferences" 
                        oncheckedchanged="chPreferences_CheckedChanged" />
                    <asp:Panel ID="Panel17" runat="server" BackColor="#CCCCCC" BorderStyle="Dotted" 
                        BorderWidth="1px" Font-Size="Small">
                        <table align="center" border="0" width="70%">
                            <tr>
                                <td bgcolor="#CCCCCC">
                                    <asp:Panel ID="PanelPref" runat="server">
                                        <br />
                                        <br />
                                    </asp:Panel>
                                </td>
                                <td align="left">
                                    <asp:Label ID="lblPriority" runat="server" Text="Priority" Visible="False"></asp:Label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chPricePref" runat="server" AutoPostBack="True" 
                                        Text="Price" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtPricePref" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chResponseTimePref" runat="server" AutoPostBack="True" 
                                        Text="Response Time" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtResponseTimePref" runat="server" Width="75px" 
                                        Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chReliabilityPref" runat="server" AutoPostBack="True" 
                                        Text="Reliability" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtReliabilityPref" runat="server" Width="75px" 
                                        Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chAuthPref" runat="server" AutoPostBack="True" 
                                        Text="Authentication" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtAuthPref" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chAvPRef" runat="server" AutoPostBack="True" 
                                        Text="Availability" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtAvPref" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chLatencyPref" runat="server" AutoPostBack="True" 
                                        Text="Latency" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtLatencyPref" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chThroughputPref" runat="server" AutoPostBack="True" 
                                        Text="Throughput" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtThroughputPref" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>                              
                        </table>
                    </asp:Panel>
                </td>
            </tr>
            <tr>
                <td style="display: inline; border-collapse: collapse" valign="top">
                    <asp:CheckBox ID="chRelaxationSet" runat="server" AutoPostBack="True" 
                        BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                        Font-Size="Small" Text="Order of Relaxation " 
                        oncheckedchanged="chRelaxationSet_CheckedChanged" />
                    <asp:Panel ID="Panel18" runat="server" BackColor="#CCCCCC" BorderStyle="Dotted" 
                        BorderWidth="1px" Font-Size="Small">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="70%">
                            <tr>
                                <td bgcolor="#CCCCCC">
                                    <asp:Panel ID="PanelPref0" runat="server">
                                        <br />
                                        <br />
                                    </asp:Panel>
                                </td>
                                <td align="left">
                                    <asp:Label ID="lblPriority0" runat="server" Text="Priority" Visible="False"></asp:Label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chPrice1" runat="server" AutoPostBack="True" Text="Price" 
                                        Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtPriceRelax" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chResponseTime1" runat="server" AutoPostBack="True" 
                                        Text="Response Time" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtResponseTimeRelax" runat="server" Width="75px" 
                                        Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chReliability1" runat="server" AutoPostBack="True" 
                                        Text="Reliability" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtReliabilityRelax" runat="server" Width="75px" 
                                        Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chAuth" runat="server" AutoPostBack="True" 
                                        Text="Authentication" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtAuthRelax" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chAv" runat="server" AutoPostBack="True" 
                                        Text="Availability" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtAvRelax" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chLatency" runat="server" AutoPostBack="True" 
                                        Text="Latency" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtLatencyRelax" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:CheckBox ID="chThroughput" runat="server" AutoPostBack="True" 
                                        Text="Throughput" Visible="False" />
                                </td>
                                <td>
                                    <asp:TextBox ID="txtThroughputRelax" runat="server" Width="75px" Visible="False"></asp:TextBox>
                                </td>
                            </tr>                            
                        </table>
                        <br />
                        <br />
                    </asp:Panel>
                </td>
            </tr>
            <tr >
                <td colspan="2" bgcolor="#003366">
                    <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" 
                        onclick="btnCancel_Click" /><asp:Button ID="btnPrevious" runat="server" Text="Previous" 
                        CausesValidation="false" nclick="btnNext_Click" 
                        onclick="btnPrevious_Click" />
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                     
                     <asp:Button ID="btnNext" runat="server" Text="Next" CausesValidation="false" 
                        nclick="btnNext_Click" onclick="btnNext_Click" />
               </td>
            </tr>              
        </table>
    
    </div>
    </form>
</body>
</html>
