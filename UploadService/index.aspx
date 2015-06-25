<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="UploadService.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head runat="server">
        <title></title>
    </head>
    <body>
        <form id="form1" runat="server">
            <div>
                <%= "abcdefghijklmnopqrstuvxwyz".ToUpper() %>
                <%= HttpUtility.UrlEncode(@"c:\temp\mypdf.pdf") %>
                <%= @"c:\temp\mypdf.pdf" %>
            </div>
        </form>
    </body>
</html>