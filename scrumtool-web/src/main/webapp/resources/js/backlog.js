dragula([ document.getElementById('1'), document.getElementById('2'),
		document.getElementById('3') ])

$(document)
		.ready(
				function() {
					$("#toggleBtn")
							.click(
									function() {
										$(".addForm").slideToggle();
										$("#toggleBtn span")
												.toggleClass(
														"glyphicon-chevron-right glyphicon-chevron-down");
									});
				});