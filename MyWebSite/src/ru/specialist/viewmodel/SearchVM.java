package ru.specialist.viewmodel;

public class SearchVM {
	
	private String search = "";

	public String getSearch() {
		return search == null ? "" : search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getSearchString()
	{
		return getSearch()==null ? "%" : "%"+getSearch()+"%";
	}

}
