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

<script type="text/javascript" charset="utf-8">
    <g:applyCodec encodeAs="none">
        $(document).ready(function() {
            new Exa.Datatable("${id}", ${data}, '${columns}', ${auto}, ${filtering}, ${ordering}, ${paging}, ${infos})
                ._init();
        });
    </g:applyCodec>
</script>