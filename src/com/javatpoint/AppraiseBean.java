package com.javatpoint;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppraiseBean {
	DBConnection db;
	String[][] paramSql;
	String[] result;
	String sql=null;
	int resultCount=0;
	String[][] rs;
	public AppraiseBean()
	{
		db=new DBConnection();
		
		
	}
	public void setQuery()
	{
		sql="select ApprEmpId=1,Section= 'Section Name1',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name2',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name3',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name4',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' where 'Preeti'=?";
	}
	public String[] getSections()
	{
		paramSql=new String[1][2];
		paramSql[0][0]="String";
		paramSql[0][1]="Preeti";
		rs=db.getConnection(sql, paramSql);
		for(int i=0;i<rs.length;i++)
		{
			for(int j=0;j<rs[0].length;j++)
			{
				System.out.print(rs[i][j]+"\t");
			}
			System.out.println();
		}
		int row=rs.length;
		result=new String[row];
		for(int i=0;i<row;i++)
		{
		    result[i]=rs[i][1];
			System.out.println(result[i]);      
		} 
		return result;
	}
		

}

