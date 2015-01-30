#{if !_applicationStatus || _applicationStatus == 'None'}
	<span class="label label-default"> Not submitted</span>
#{/if} 
#{if _applicationStatus == 'Pending'}
	<span class="label label-primary">Pending</span>
#{/if} 
#{if _applicationStatus == 'Accepted'}
	<span class="label label-success"> Accepted</span>
#{/if} 
#{if _applicationStatus == 'Rejected'}
	<span class="label label-default"> Rejected</span>
#{/if} 
#{if _applicationStatus == 'Holding'}
	<span class="label label-warning"> Wait-listed</span>
#{/if}
