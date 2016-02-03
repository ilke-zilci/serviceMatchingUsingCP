<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Copy (2) of result.aspx.cs" Inherits="result" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml"><body><form id="form1" runat="server">
    
        <table border="0" cellpadding="0" cellspacing="0" style="border: medium solid #C0C0C0; " align="center">
            <tr>
                 <td colspan="2" align="center" bgcolor="#003366" style="font-size: small; color: #FFFFFF; font-weight: bolder;" >Step 
                     6 - request and results</td>
            </tr>            
            <tr>
                <td align="center" >
                    &nbsp;
                    <asp:Panel ID="Panel2" runat="server" GroupingText="Request" Width="98%">
                        <asp:GridView ID="GridView1" runat="server" CellPadding="2" Font-Size="Small" 
                        ForeColor="#333333" AutoGenerateColumns="False" Width="60%" >
                            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                            <Columns>
                                <asp:TemplateField HeaderText="Constraint_Id" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblQoSConstraint_Id" runat="server" 
                                        Text='<%# Bind("QoSConstraint_Id") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="Attribute Name" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblname" runat="server" Text='<%# Bind("name") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="Unit" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblUnit" runat="server" Text='<%# Bind("Unit") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="type" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lbltype" runat="server" Text='<%# Bind("type") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="tendency" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lbltendency" runat="server" Text='<%# Bind("tendency") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="Values" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:TextBox ID="txtDetails" runat="server" ></asp:TextBox>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="weight" Visible="False" 
                                ItemStyle-VerticalAlign="Middle" ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblweight" runat="server" Text='<%# Bind("weight") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="relaxation" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblrelaxation" runat="server" Text='<%# Bind("relaxation") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="preference" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblpreference" runat="server" Text='<%# Bind("preference") %>'></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                            </Columns>
                            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <EditRowStyle BackColor="#999999" />
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                        </asp:GridView>
                    </asp:Panel>
                </td>
            </tr>
            <tr>
                <td align="center">
                    &nbsp;</td>
            </tr>
            <tr>
                <td align="center">
                    <asp:Panel ID="Panel1" runat="server" BorderColor="#000099" BorderStyle="None" 
                        GroupingText="Results" Width="98%" Visible="False" >
                        <asp:GridView ID="GridView2" runat="server" CellPadding="4" Font-Size="Small" 
                        ForeColor="#333333" AutoGenerateColumns="False" HorizontalAlign="Center" 
                            AllowSorting="True" PageSize="31">
                            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                            <Columns>
                                <asp:TemplateField HeaderText="Service ID" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:Label ID="lblSID" runat="server"></asp:Label>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="service" ItemStyle-VerticalAlign="Middle" 
                                ItemStyle-HorizontalAlign="Center" Visible="False">
                                    <ItemTemplate>
                                        <asp:Label ID="lblInfo" runat="server"></asp:Label>
                                        <asp:GridView ID="GridView3" runat="server" BackColor="White" 
                                        BorderColor="#DEDFDE" BorderStyle="None" BorderWidth="1px" CellPadding="4" 
                                        ForeColor="Black" GridLines="Vertical">
                                            <RowStyle BackColor="#F7F7DE" />
                                            <FooterStyle BackColor="#CCCC99" />
                                            <PagerStyle BackColor="#F7F7DE" ForeColor="Black" HorizontalAlign="Right" />
                                            <SelectedRowStyle BackColor="#CE5D5A" Font-Bold="True" ForeColor="White" />
                                            <HeaderStyle BackColor="#6B696B" Font-Bold="True" ForeColor="White" />
                                            <AlternatingRowStyle BackColor="White" />
                                        </asp:GridView>
                                        <asp:TextBox ID="txtInfo" runat="server" TextMode="MultiLine" Width="200px" 
                                        Height="145px" ></asp:TextBox>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                                <asp:TemplateField HeaderText="rank" ItemStyle-VerticalAlign="Middle" SortExpression="rank"
                                ItemStyle-HorizontalAlign="Center">
                                    <ItemTemplate>
                                        <asp:TextBox ID="txtRank" runat="server" ></asp:TextBox>
                                    </ItemTemplate>
                                    <ItemStyle HorizontalAlign="Center" VerticalAlign="Middle" />
                                </asp:TemplateField>
                            </Columns>
                            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                            <EditRowStyle BackColor="#999999" />
                            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                        </asp:GridView>
                    </asp:Panel>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    <asp:Button ID="btnExcel" runat="server" onclick="Button1_Click" 
                        Text="show results"/>
                    
                    <asp:Panel ID="Panel3" runat="server">
                        <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
                        <asp:Label ID="Label2" runat="server" Text="Label"></asp:Label>
                        <br />
                        <asp:TextBox ID="TextBox1" runat="server" TextMode="MultiLine"></asp:TextBox>
                        <asp:TextBox ID="TextBox2" runat="server" TextMode="MultiLine"></asp:TextBox>
                        <asp:TextBox ID="TextBox3" runat="server" TextMode="MultiLine"></asp:TextBox>
                        <br />
                        <asp:TextBox ID="TextBox4" runat="server" TextMode="MultiLine"></asp:TextBox>
                        <asp:TextBox ID="TextBox5" runat="server" TextMode="MultiLine"></asp:TextBox>
                        <asp:TextBox ID="TextBox6" runat="server" TextMode="MultiLine"></asp:TextBox>
                    
                    </asp:Panel>

                </td>
            </tr>
            <tr>
                <td>
                    <asp:Panel ID="Panel4" runat="server" GroupingText="Results" 
                        HorizontalAlign="Center">
                        <asp:Panel ID="Panel5" runat="server" GroupingText="Exact Match">
                            <asp:GridView ID="GridView4" runat="server" AutoGenerateColumns="False" 
                                BorderStyle="Solid" BorderWidth="1px" CellPadding="4" 
                                DataSourceID="SqlDataSource1" Font-Size="Small" ForeColor="#333333">
                                <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                                <Columns>
                                    <asp:BoundField DataField="Sname" HeaderText="Sname" SortExpression="Sname" />
                                    <asp:BoundField DataField="rank" HeaderText="rank" SortExpression="rank" />
                                    <asp:BoundField DataField="responsetime" HeaderText="responsetime" 
                                        SortExpression="responsetime" />
                                    <asp:BoundField DataField="availability" HeaderText="availability" 
                                        SortExpression="availability" />
                                    <asp:BoundField DataField="latency" HeaderText="latency" 
                                        SortExpression="latency" />
                                    <asp:BoundField DataField="throughput" HeaderText="throughput" 
                                        SortExpression="throughput" />
                                </Columns>
                                <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                                <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                                <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                <EditRowStyle BackColor="#999999" />
                                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            </asp:GridView>
                        </asp:Panel>
                        <br />
                        <asp:Panel ID="Panel6" runat="server" GroupingText="Partial Match">
                            <asp:GridView ID="GridView5" runat="server" AutoGenerateColumns="False" 
                                BorderStyle="Solid" BorderWidth="1px" CellPadding="4" 
                                DataSourceID="SqlDataSource2" Font-Size="Small" ForeColor="#333333">
                                <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                                <Columns>
                                    <asp:BoundField DataField="Sname" HeaderText="Sname" SortExpression="Sname" />
                                    <asp:BoundField DataField="rank" HeaderText="rank" SortExpression="rank" />
                                    <asp:BoundField DataField="responsetime" HeaderText="responsetime" 
                                        SortExpression="responsetime" />
                                    <asp:BoundField DataField="availability" HeaderText="availability" 
                                        SortExpression="availability" />
                                    <asp:BoundField DataField="latency" HeaderText="latency" 
                                        SortExpression="latency" />
                                    <asp:BoundField DataField="throughput" HeaderText="throughput" 
                                        SortExpression="throughput" />
                                </Columns>
                                <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                                <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                                <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                <EditRowStyle BackColor="#999999" />
                                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                            </asp:GridView>
                        </asp:Panel>
                        <br />
                    </asp:Panel>
                    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:testConnectionString %>" 
                        
                        SelectCommand="SELECT [Sname], [rank], [responsetime], [availability], [latency], [throughput] FROM [tbl_Hard_selected] ORDER BY [rank]">
                    </asp:SqlDataSource>
                    <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:testConnectionString %>" 
                        
                        SelectCommand="SELECT [Sname], [rank], [responsetime], [availability], [latency], [throughput] FROM [tbl_Selected] ORDER BY [rank]">
                    </asp:SqlDataSource>
                </td>
            </tr>
            <tr>
                <td bgcolor="#003366" colspan="2" >
                    <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" 
                        onclick="btnCancel_Click" />
                     <asp:Button ID="btnPrevious" runat="server" Text="Previous" 
                        CausesValidation="false" nclick="btnNext_Click" 
                        onclick="btnPrevious_Click" Visible="False" />
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <asp:Button ID="btnNext" runat="server" Text="Next" CausesValidation="false" 
                        nclick="btnNext_Click" onclick="btnNext_Click" Visible="False" />
               </td>
            </tr>            
        </table>
    
   </form>
</body>
</html>

