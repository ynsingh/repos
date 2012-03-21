<g:select name="projects" from="${projectList}" optionKey="id" optionValue="code"  value="${fieldValue(bean:externalFundAllocationInstance?.grantAllocation?.projects, field: 'projects')}" noSelection="['null':'-Select-']" />

