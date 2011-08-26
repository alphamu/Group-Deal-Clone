package com.groupdealclone.app.validation;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Set;

import com.groupdealclone.app.domain.CampaignCategories;
import com.groupdealclone.app.domain.Category;
import com.groupdealclone.app.service.CategoryManager;

public class CustomCategoryPropertyEditor extends PropertyEditorSupport {

	CategoryManager	categoryManager;

	public CustomCategoryPropertyEditor(CategoryManager catMan) {
		categoryManager = catMan;
	}

	@Override
	public void setValue(Object value) {
		if (value == null)
			super.setValue("");

		if (value instanceof CampaignCategories) {
			Set<Category> cat = ((CampaignCategories) value).getCategories();
			if (cat != null && cat.size() > 0) {
				StringBuffer sb = null;
				for (Category c : cat) {
					if (sb != null) {
						sb.append("," + c.getName());
					} else {
						sb = new StringBuffer(c.getName());
					}
				}
				super.setValue(sb.toString());
			} else {
				super.setValue("");
			}
		} else
			super.setValue("");
	}

	@Override
	public void setAsText(String value) {
		if (value != null) {
			String val = value.trim();
			if (val.length() == 0 || val.equals(",")) {
				super.setValue(null);
				return;
			}

			String[] namesIn = val.split(",");
			for (int i = 0; i < namesIn.length; i++) {
				namesIn[i] = namesIn[i].trim();
			}
			Set<Category> cats = new HashSet<Category>(categoryManager.getCategories(namesIn));
			CampaignCategories cc = new CampaignCategories();
			cc.setCategories(cats);
			super.setValue(cc);
		}
	}
}
