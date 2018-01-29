package com.javatpoint;

public class SaveAppraiseBean {
	String[][] selfappr;
	String[] sec;
	String name;
	int flag=0;int count=0;
	public void setVal(String s)
	{
		selfappr[0][0]=s;
	}
	public String getVal()
	{
		System.out.println(selfappr[0][0]);
		return selfappr[0][0];
	}
	public void setSec(String[] sec)
	{
		this.sec=sec;
	}
	public String[] getSec()
	{
		return sec;
	}
	public void setSelfappr(String[][] selfappr)
	{
		this.selfappr=selfappr;
	}
	public String[][] getSelfappr()
	{
		return selfappr;
	}
	public void setAppraiseStatus(int flag)
	{
		this.flag=flag;
	}
	public String getAppraiseStatus()
	{ 
		switch(flag)
		{
		case 1: return "Form saved successfully";
		case 2: return "Can not submit incomplete form";
		case 3: return "Form submitted successfully and mailed to your supervisor";
		default: return "";
		}
	}
	public int saveForm()
	{
		int rowupdated=0;
		String sql="update appr_empl_rating	set remarks=?,rating=? where ApprEmpRatingId=?";
		UpdateDB ob=new UpdateDB();
		String[][] paramSql=new String[3][2];
		int row=selfappr.length;
		for(int i=0;i<row;i++)
		{
			paramSql[0][0]="String";
			paramSql[0][1]=selfappr[i][3];
			paramSql[1][0]="int";
			paramSql[1][1]=selfappr[i][4];
			paramSql[2][0]="int";
			paramSql[2][1]=selfappr[i][10];
			rowupdated+=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");
		}
		System.out.println(rowupdated+" total");
		return rowupdated;
	}
	public void submitForm()
	{
		
		int len=selfappr.length;
		int rowsaved=this.saveForm();
		for(int i=0;i<len;i++)
		{
			if(selfappr[i][3].equals("null")||selfappr[i][3].equals(null)||selfappr[i][3].equals("") || selfappr[i][4].equals("")|| selfappr[i][4].equals("0"))
				count++;
			
			System.out.println(i + " - " + count);
		}
		
		if(count==0)
		{
			int rowupdated=0;
			System.out.println(rowsaved+" saved");
			
			String sql="update appr_empl_flow set Status = 2 where apprEmpId= ? and phaseid=?";
			UpdateDB ob=new UpdateDB();
			String[][] paramSql=new String[2][2];
			int row=selfappr.length;
			for(int i=0;i<row;i++)
			{
				paramSql[0][0]="int";
				paramSql[0][1]=selfappr[i][0];
				paramSql[1][0]="int";
				paramSql[1][1]=selfappr[i][11];
				
			}
			rowupdated=ob.getConnection(sql, paramSql);
			System.out.println(rowupdated+" row");		
			SendMail sm=new SendMail();
			sm.sendMail();
		}
	}
	public int getCount()
	{
		return count;
	}
}
