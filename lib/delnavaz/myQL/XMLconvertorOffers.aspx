<%@ Page Language="C#" AutoEventWireup="true" CodeFile="XMLconvertorOffers.aspx.cs" Inherits="XMLconvertorOffers" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title>Untitled Page</title>
    </head>
<body>
    <form id="form1" runat="server">
                    <table align="center" border="0" style="border: medium solid #C0C0C0; ">
                        <tr>
                            <td colspan="3" align="center" bgcolor="#003366" 
                                style="font-size: small; color: #FFFFFF; font-weight: bolder;" >converting Offers to XML</td>
                        </tr>
                        <tr>
                            <td>
                                <asp:Label ID="lblKey" runat="server" Text="Keyword"></asp:Label>
                            </td>
                            <td colspan="2">
                                <asp:DropDownList ID="ddlKey" runat="server">
                                    <asp:ListItem>---</asp:ListItem>
                                    <asp:ListItem>development</asp:ListItem>
                                    <asp:ListItem>commerce</asp:ListItem>
                                    <asp:ListItem>weather</asp:ListItem>
                                    <asp:ListItem>business</asp:ListItem>
                                    <asp:ListItem>sequence</asp:ListItem>
                                    <asp:ListItem>protein</asp:ListItem>
                                    <asp:ListItem>management</asp:ListItem>
                                    <asp:ListItem>google</asp:ListItem>
                                    <asp:ListItem>map</asp:ListItem>
                                    <asp:ListItem>Net</asp:ListItem>
                                    <asp:ListItem>flight</asp:ListItem>
                                    <asp:ListItem>amazon</asp:ListItem>
                                </asp:DropDownList>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td></td>
                            
                        </tr>
                        <tr>
                            <td >
                                <asp:Panel ID="Panel3" runat="server" GroupingText="attributes" 
                                    Font-Size="Medium" HorizontalAlign="Center">
                                    <asp:ListBox ID="ListBox1" runat="server" SelectionMode="Multiple" Font-Size="Small" Height="190px" 
                                        Width="120px">
                                        <asp:ListItem>response time</asp:ListItem>
                                        <asp:ListItem>availability</asp:ListItem>
                                        <asp:ListItem>throughput</asp:ListItem>
                                        <asp:ListItem>compliance</asp:ListItem>
                                        <asp:ListItem>best practices</asp:ListItem>
                                        <asp:ListItem>latency</asp:ListItem>
                                        <asp:ListItem>documentation</asp:ListItem>
                                        <asp:ListItem>price</asp:ListItem>
                                        <asp:ListItem>reliability</asp:ListItem>
                                        <asp:ListItem>authentication</asp:ListItem>
                                        <asp:ListItem>successability</asp:ListItem>
                                    </asp:ListBox>                                
                                </asp:Panel>
                            </td>
                            <td>
                                    <asp:Button ID="btnAddList" runat="server" Text="&gt;" 
                                        onclick="btnAddList_Click" Width="30px" style="height: 26px" />
                            
                                    <br />
                                    <asp:Button ID="btnRemoveList" runat="server" onclick="btnRemoveList_Click" 
                                        Text="&lt;" Width="30px"  />
                            
                            </td>
                            <td>
                                <asp:Panel ID="Panel4" runat="server" GroupingText="selected attributes" 
                                    Font-Size="Medium" HorizontalAlign="Center">
                                    <asp:ListBox ID="ListBox2" runat="server" AutoPostBack="True" 
                                        SelectionMode="Multiple" Font-Size="Small" Height="190px" Width="120px"></asp:ListBox>                                
                                
                                </asp:Panel>                                
                            
                            </td>
                        </tr>                        
                        <tr>
                            <td colspan="3" bgcolor="#003366">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <asp:Button ID="btnConvert" runat="server" Text="Convert" CausesValidation="false" 
                                    onclick="btnNext_Click" />
                            </td>
                        </tr>
                    </table>
    </form>
</body>
</html>

