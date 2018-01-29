package com.javatpoint;



public class AppraiseBean {
	DBConnection db;
	String[][] paramSql;
	String[] result;
	String sql1=null,sql2=null;
	int resultCount=0,row=0,col=0,flag=0;
	String[][] rs;
	String[][] section_form;
	public AppraiseBean()
	{
		db=new DBConnection();
		
	}
	public void setQuery()
	{
		sql1="	select * from view_getappraisalrecords where ApprEmpId=? and phaseid=1 order by SectionColOrder, QuestionColOrder";

		sql2="select * from view_getappraisalrecords where user_name=? and section=? and phaseid=1 order by SectionColOrder, QuestionColOrder";

}
	public String[] getSections(int apprempid)
	{
		if(flag==0)
		this.getAllForms(apprempid);
		row=rs.length;
		col=rs[0].length;
		String temp="";
		int count=0;
		for(int i=0;i<row;i++)
		{
			if(!temp.equals(rs[i][1]))
			{
				count++;	
				temp=rs[i][1];
			}
			
		}
		result=new String[count];
		temp="";int indx=0;
		for(int i=0;i<row;i++)
			{
				if(!temp.equals(rs[i][1]))
				{
					result[indx]=rs[i][1];
					temp=rs[i][1];
					indx++;
				}
				
			}
		return result;
	}
	public String[][] getForm(int section)
	{
		row=rs.length;
		col=rs[0].length;
		int size=0;
		for(int i=0;i<row;i++)
		{
			if(rs[i][1].equals(result[section]))
			{
				size++;
			}
		}
		section_form=new String[size][col];
		int indx=0;
		for(int i=0;i<row;i++)
		{
			if(rs[i][1].equals(result[section]))
			{
				for(int j=0;j<col;j++)
				{
					section_form[indx][j]=rs[i][j];
					
				}
				indx++;
			}
		}
		return section_form;
		
	}
	public String[][] getAllForms(int apprempid)
	{
		paramSql=new String[1][2];
		paramSql[0][0]="int";
		paramSql[0][1]=Integer.toString(apprempid);
		rs=db.getConnection(sql1, paramSql);
		return rs;
	}
	public void setUpdatedForms(String[][] forms)
	{
		rs=forms;
		flag=1;
	}
}

