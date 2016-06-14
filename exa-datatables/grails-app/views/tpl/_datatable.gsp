<span class="dataTables_wrapper">
    <table id="${id}" class="table display ${cssClass}" cellspacing="0" width="100%">
        <thead>
            <tr>
                <g:each in="${headers}" var="header">
                    <th>
                        <g:if test="${header.isCustom}">
                            ${header.value}
                        </g:if>
                        <g:else>
                            <g:message code="exa.datatable.${header.name}.label" default="${header.name}" />
                        </g:else>
                    </th>
                </g:each>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
</span>

<g:javascript>
if (typeof jQuery !== 'undefined') {
    (function($) {
        <g:encodeAs encodeAs="none" codec="none">
            new Exa.Datatable("${id}", ${data}, '${columns}', ${auto}, ${filtering}, ${ordering}, ${paging}, ${stateSave}, ${infos})
                ._init();
        </g:encodeAs>
    })(jQuery);
}
else {
    console.error("JQuery 1.x or 2.x is required for exa-datatables Grails plugin.");
}
</g:javascript>