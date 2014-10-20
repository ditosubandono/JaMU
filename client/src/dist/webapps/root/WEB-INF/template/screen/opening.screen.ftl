<@compress single_line=false>
    <#include "/layout/index.ftl">
${header}
<h2 class="text-center">SILAHKAN MASUKKAN NOMOR REKENING</h2>
<form class="form" action="${rc.getContextPath()}/dashboard/account.action" method="post">
<table class="table table-condensed text-left">
    <tr>
        <td>
            <table class="table table-condensed">
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td>&nbsp;<input class="form-control" autofocus="true" name="accountNumber"></td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
        <td></td>
        <td>
            <table class="table table-condensed text-right">
                <tr>
                    <td>&nbsp;</td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
                <tr>
                    <td><h2>TEKAN TOMBOL</h2></td>
                    <td><input type="submit" class="btn btn-default btn-lg" value="&nbsp;>>>"></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</form>
${footer}
</@compress>