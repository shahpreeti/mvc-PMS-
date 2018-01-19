package com.javatpoint;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		sql1="select ApprEmpId=1,Section= 'Section Name1',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind1',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name2',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name3',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name4',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' where 'Preeti'=?";
		sql2="select ApprEmpId=1,Section= 'Section Name1',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind2',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name2',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind2',PerformanceInd2 = 'Curr Ind2',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name3',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind3',PerformanceInd2 = 'Curr Ind3',PerformanceInd3 = 'Next Ind',AppraisalStatus = '1' union all select ApprEmpId=1,Section= 'Section Name4',Question = 'Question',Remarks = 'Remakrs',Rating ='Rating',PerformanceInd1 = 'Prev Ind4',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind4',AppraisalStatus = '1' where 'Section'=?";//need to assign query
	}
	public String[] getSections()
	{
		paramSql=new String[1][2];
		paramSql[0][0]="String";
		paramSql[0][1]="Preeti";
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
		temp="";
		for(int i=0;i<row;i++)
			{
				if(!temp.equals(rs[i][1]))
				{
					result[i]=rs[i][1];
				}
				
			}
		return result;
		//
		/*for(int i=0;i<row;i++)
		{
		    result[i]=rs[i][1];
			System.out.println(result[i]);      
		} 
		return result;*/
	}
		
	public String[][] getForms(int section)
	{
		paramSql=new String[1][2];
		paramSql[0][0]="String";
		paramSql[0][1]=result[section];
		section_form=db.getConnection(sql2, paramSql);
		return section_form;
	}
}

