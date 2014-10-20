<@compress single_line=false>
<#include "/layout/index.ftl">
${header}
<h2 class="text-center">SILAHKAN MEMILIH TRANSAKSI</h2>
<table class="table table-condensed text-left">
    <tr>
        <td>
            <table class="table table-condensed">
                <tr>
                    <td><a href="${rc.getContextPath()}/dashboard/inquiry/${acc!}" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td><h2>INFO SALDO</h2></td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td><h2>PENARIKAN TUNAI</h2></td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td><h2>TRANSFER</h2></td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                    <td><h2>ISI ULANG VOUCHER</h2></td>
                </tr>
            </table>
        </td>
        <td>&nbsp;<h2><#if message??>${message!}</#if></h2></td>
        <td>
            <table class="table table-condensed text-right">
                <tr>
                    <td><h2>PEMBAYARAN</h2></td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
                <tr>
                    <td><h2>PEMBELIAN</h2></td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
                <tr>
                    <td><h2>GANTI PIN</h2></td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
                <tr>
                    <td><h2>PENDAFTARAN</h2></td>
                    <td><a href="#" class="btn btn-default btn-lg">&nbsp;>>></a></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
${footer}
</@compress>