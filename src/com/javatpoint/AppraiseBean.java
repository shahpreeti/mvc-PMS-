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
		sql1="select appr_empl.ApprEmpId,Appr_Sections.ApprSectionName Section,Appr_Questions.ApprQuestionName Question,appr_empl_rating.Remarks Remarks,appr_empl_rating.Rating Rating,PerformanceInd1 = 'Prev Ind1',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',1 AppraisalStatus,appr_questions.RatingYN " + 
				" from appr_empl " + 
				" inner join appr_empl_rating on appr_empl.ApprEmpId=appr_empl_rating.ApprEmpId " + 
				" inner join appr_questions on appr_empl_rating.ApprQuestionId=Appr_Questions.ApprQuestionID " + 
				" inner join appr_sections on Appr_Questions.ApprSectionId=Appr_Sections.ApprSectionId " + 
				" inner join appr_empl_flow on appr_empl_flow.ApprEmpId=appr_empl.ApprEmpID and appr_empl_flow.phaseid=appr_empl_rating.appr_phase_id " + 
				" inner join ohrm_user on ohrm_user.emp_number=appr_empl_flow.hs_hr_employee_id " + 
				" where  appr_empl_flow.phaseid=1 and ohrm_user.user_name=? " + 
				" order by appr_sections.ColOrder,appr_questions.ColOrder";
		sql2="select appr_empl.ApprEmpId,Appr_Sections.ApprSectionName Section,Appr_Questions.ApprQuestionName Question,appr_empl_rating.Remarks Remarks,appr_empl_rating.Rating Rating,PerformanceInd1 = 'Prev Ind1',PerformanceInd2 = 'Curr Ind',PerformanceInd3 = 'Next Ind',1 AppraisalStatus,appr_questions.RatingYN " + 
				" from appr_empl " + 
				" inner join appr_empl_rating on appr_empl.ApprEmpId=appr_empl_rating.ApprEmpId " + 
				" inner join appr_questions on appr_empl_rating.ApprQuestionId=Appr_Questions.ApprQuestionID " + 
				" inner join appr_sections on Appr_Questions.ApprSectionId=Appr_Sections.ApprSectionId " + 
				" inner join appr_empl_flow on appr_empl_flow.ApprEmpId=appr_empl.ApprEmpID and appr_empl_flow.phaseid=appr_empl_rating.appr_phase_id " + 
				" inner join ohrm_user on ohrm_user.emp_number=appr_empl_flow.hs_hr_employee_id " + 
				" where  appr_empl_flow.phaseid=1 and ohrm_user.user_name=? and Appr_Sections.ApprSectionName=? " + 
				" order by appr_sections.ColOrder,appr_questions.ColOrder";
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

