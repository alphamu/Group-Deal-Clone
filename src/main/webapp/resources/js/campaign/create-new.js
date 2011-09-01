	
		$(function() {

			$("#company\\.name").autocomplete({
				source : companyList,
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						$("#company\\.id").val(ui.item.value);
						$(this).val(ui.item.label);
					}
					return false;
				}
			});

			$("#campaignCategories").autocomplete({
				source : categoryList,
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						var val = $(this).val();
						if (val.lastIndexOf(',') == -1) {
							$(this).val(ui.item.label);
						} else {
							val = val.substring(0, val.lastIndexOf(',') + 1);
							val += ui.item.label;
							$(this).val(val);
						}
						return false;
					}
				}
			});

			$("#campaignCities").autocomplete({
				source : cityList,
				minLength : 2,
				select : function(event, ui) {
					if (ui.item) {
						var val = $(this).val();
						if (val.lastIndexOf(',') == -1) {
							$(this).val(ui.item.label);
						} else {
							val = val.substring(0, val.lastIndexOf(',') + 1);
							val += ui.item.label;
							$(this).val(val);
						}
						return false;
					}
				}
			});
		});