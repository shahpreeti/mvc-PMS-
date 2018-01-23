package com.javatpoint;



public class AppraiseBean {
	DBConnection db;
	String[][] paramSql;
	String[] result;
	String sql1=null,sql2=null;
	int resultCount=0,row=0,col=0;
	String[][] rs,section_form;
	public AppraiseBean()
	{
		db=new DBConnection();
		
	}
	public void setQuery()
	{
		sql1="select * from view_getappraisalrecords where user_name=? and phaseid=1 order by SectionColOrder, QuestionColOrder";

		sql2="select * from view_getappraisalrecords where user_name=? and section=? and phaseid=1 order by SectionColOrder, QuestionColOrder";

}
	public String[] getSections(String user)
	{
		paramSql=new String[1][2];
		paramSql[0][0]="String";
		paramSql[0][1]=user;
		rs=db.getConnection(sql1, paramSql);
		row=rs.length;
		col=rs[0].length;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<rs[0].length;j++)
			{
				System.out.print(rs[i][j]+"\t");
			}
			System.out.println();
		}
		
		//
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
		
	public String[][] getForms(String user,int section)
	{
		paramSql=new String[2][2];
		
		paramSql[0][0]="String";
		paramSql[0][1]=user;
		paramSql[1][0]="String";
		paramSql[1][1]=result[section];
		section_form=db.getConnection(sql2, paramSql);
		return section_form;
	}
}

