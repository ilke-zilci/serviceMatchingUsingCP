<%@ Page Language="C#" AutoEventWireup="true" CodeFile="step3.aspx.cs" Inherits="step3" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
    
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <table align="center" border="0" style="border: medium solid #C0C0C0; ">
            <tr>
                <td colspan="3" align="center" bgcolor="#003366" 
                 style="font-size: small; color: #FFFFFF; font-weight: bolder;" >Step 3 - Browsing QoS attributes values</td>
            </tr>
            <tr>
                <td>
                    <br />
                    
                    <asp:TreeView ID="trvChildClusters" runat="server" 
                    onselectednodechanged="trvChildClusters_SelectedNodeChanged" Width="90%" >
                        <HoverNodeStyle BackColor="#99CCFF" />
                        <SelectedNodeStyle BorderStyle="Dotted" />
                    </asp:TreeView>
                </td>
                <td>
                    <br />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <asp:TextBox ID="txtTest" runat="server" BackColor="#FFCCFF" 
                        TextMode="MultiLine" Visible="False"></asp:TextBox>
                    <br />
                    <asp:Label ID="lblClustersDetails" runat="server" 
                        Text="Details of the selected cluster"></asp:Label>
                </td>
            </tr>
            <tr>
                <td colspan="2">  
                    <asp:TextBox ID="txtSummary" runat="server" BackColor="White" 
                    TextMode="MultiLine" style="margin-bottom: 18px" Height="100px" Width="95%" 
                        Visible="False" ></asp:TextBox>
                    <asp:TextBox ID="txtResult" runat="server" BackColor="White" 
                    TextMode="MultiLine" Height="87px" Width="90%" ></asp:TextBox>
                </td>
            </tr>
            <tr >
                <td colspan="2">
                    <asp:Label ID="Label1" runat="server" Text="Summaries"></asp:Label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <asp:TextBox ID="txtClustersDetails" runat="server" BackColor="White" 
                    TextMode="MultiLine" Height="100px" Width="95%" ></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
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
