package com.webank.webase.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.webank.webase.Application;
import com.webank.webase.transaction.TransactionService;

import javax.swing.JButton;

@SpringBootApplication
public class GUI extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame window;
	private Label label1;
	private JButton signUp;
	private JFrame page1;
	private JButton company;
	private JButton bank;
	private JTextField nametext;
	private JFrame Companyregister;
	private JFrame CompanyQuery;
	private JTextField numtext;
	private JFrame TransPage;
	private JFrame TransingPage;
	private JTextField fromText;
	private JTextField toText;
	private JTextField amountText;
	private JTextField contentText;
	private JTextField receiptNumText;
	private JTextField statusText;
	private JFrame FinancingPage;
	
	private JFrame Bankregister;
	private JFrame checkPage;
	private JTextField bankNumText;
	private static ConfigurableApplicationContext applicationContext;
	public static void main(String[] args) {
     applicationContext = SpringApplication.run(Application.class, args);
      //TransactionService tranService = applicationContext.getBean(TransactionService.class);
      //tranService.sendPostTransaction();
      //GUI gui=new GUI();
  }
	public GUI(){
		//applicationContext = SpringApplication.run(Application.class, args);
		zeropage();
	}
	@Bean
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);// ms
        factory.setConnectTimeout(10000);// ms
        return new RestTemplate(factory);
    }
	public void zeropage() {
		window= new JFrame("WeBank");
		window.setLayout(null);
		window.setSize(600,400);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		
		label1= new Label("WeBank");
		label1.setBounds(250,0, 100, 50);
		Font font=new Font("宋体",Font.PLAIN,25);
		label1.setFont(font);
		//label1.setBackground(Color.blue);
		label1.setAlignment(1);
		window.add(label1);
		Label label2=new Label("This is a application to help company transaction");
		Font font1=new Font("宋体",Font.PLAIN,15);
		label2.setFont(font1);
		label2.setBounds(0, 100, 600, 30);
		label2.setAlignment(1);
		window.add(label2);
		
		signUp=new JButton("进入操作界面");
		signUp.setSize(200,40);
		signUp.setLocation(200,200);
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				firstPage();
			}
		});
		window.add(signUp);
		
		window.setVisible(true);
	}
	public void firstPage() {
		page1=new JFrame("WeBank");
		page1.setLayout(null);
		page1.setSize(600, 400);
		label1.setBounds(0, 0, 600, 50);
		label1.setText("Welcome!");
		page1.add(label1);
		
		company=new JButton("公司");
		company.setSize(200, 100);
		company.setLocation(90, 150);
		company.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				companyPage();
			}
			
		});
		page1.add(company);
		
		bank=new JButton("银行");
		bank.setSize(200,100);
		bank.setLocation(310, 150);
		bank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bankPage();
			}
			
		});
		page1.add(bank);
		
		page1.setVisible(true);
		window.setVisible(false);
	}
	public void companyPage() {
		//注册
		//信息
		JFrame companypage=new JFrame("Company");
		companypage.setLayout(null);
		companypage.setSize(200, 300);
		
		JButton register=new JButton("公司注册");
		register.setSize(100, 50);
		register.setLocation(50,50);
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Companyregister=new JFrame("CompanyRegister");
				Companyregister.setLayout(null);
				Companyregister.setSize(300,300);
				
				Label label2=new Label("Please input your company name");
				label2.setBounds(0,20,300,50);
				label2.setAlignment(1);
				Companyregister.add(label2);
			
				nametext= new JTextField();
				nametext.setBounds(75, 100, 150, 25);
				Companyregister.add(nametext);
				
				Label name=new Label("name");
				name.setBounds(10, 100, 55, 25);
				name.setAlignment(2);
				Companyregister.add(name);
				
				JButton register=new JButton("注册");
				register.setBounds(100, 150, 100, 25);
				register.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String str1=nametext.getText();
						TransactionService tranService = applicationContext.getBean(TransactionService.class);
						String str2=tranService.sendPostTransaction("register_Company","[\""+str1+"\"]");
						if(str2!=null) {
							Label result=new Label(str2);
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							Companyregister.add(result);
						}else {
							Label result=new Label("failed");
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							Companyregister.add(result);
						}
					}
				});
				Companyregister.add(register);
				
				Companyregister.setVisible(true);
				
			}
			
		});
		companypage.add(register);
		
		JButton query=new JButton("信息查询");
		query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CompanyQuery=new JFrame("Company Query");
				CompanyQuery.setSize(300,350);
				CompanyQuery.setLayout(null);
				
				Label num=new Label("编号");
				num.setBounds(0, 50, 100, 25);
				num.setAlignment(1);
				CompanyQuery.add(num);
				
				numtext=new JTextField(); 
				numtext.setBounds(110, 50, 100, 25);
				CompanyQuery.add(numtext);
				
				JButton query=new JButton("查询");
				query.setBounds(100, 100, 100, 25);
				query.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String str1=numtext.getText();
						TransactionService tranService = applicationContext.getBean(TransactionService.class);
						String str2=tranService.sendGetTransaction("print_Company","[\""+str1+"\"]");
						if(str2!=null) {
							
							List<Object> str4=JSONArray.parseArray(str2);
							
							Label str3=new Label("name : "+str4.get(0).toString());
							System.out.print(str2);
							str3.setBounds(0, 150, 300, 25);
							str3.setBackground(Color.white);
							CompanyQuery.add(str3);
							
							Label str5=new Label("number : "+str4.get(1).toString());
							System.out.print(str5);
							str5.setBounds(0, 200, 300, 25);
							str5.setBackground(Color.white);
							CompanyQuery.add(str5);
							
							Label str6=new Label("borrow_bank : "+str4.get(2).toString());
							System.out.print(str6);
							str6.setBounds(0, 250, 300, 25);
							str6.setBackground(Color.white);
							CompanyQuery.add(str6);
						}
					}
				});
				CompanyQuery.add(query);
				CompanyQuery.setVisible(true);
			}
			
		});
		query.setSize(100,50);
		query.setLocation(50, 100);
		companypage.add(query);
		
		JButton  trans=new JButton("账单");
		trans.setSize(100,50);
		trans.setLocation(50, 150);
		trans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TransPage=new JFrame("TransPage");
				TransPage.setLayout(null);
				TransPage.setSize(300, 450);
				//交易
				JButton transbutton=new JButton("进行交易");
				transbutton.setBounds(100, 50, 100, 50);
				transbutton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						TransingPage=new JFrame("进行交易");
						TransingPage.setLayout(null);
						TransingPage.setSize(300, 400);
						
						Label title=new Label("交易进行界面");
						title.setAlignment(1);
						title.setBounds(0, 0, 300, 50);
						TransingPage.add(title);
						
						Label from=new Label("from : ");
						from.setAlignment(1);
						from.setBounds(0, 75, 100, 25);
						TransingPage.add(from);
						fromText=new JTextField();
						fromText.setBounds(100,75,150,25);
						TransingPage.add(fromText);
						
						Label to=new Label("to : ");
						to.setAlignment(1);
						to.setBounds(0, 125, 100, 25);
						TransingPage.add(to);
						toText=new JTextField();
						toText.setBounds(100,125,150,25);
						TransingPage.add(toText);
						
						Label amount=new Label("amount : ");
						amount.setAlignment(1);
						amount.setBounds(0, 175, 100, 25);
						TransingPage.add(amount);
						amountText=new JTextField();
						amountText.setBounds(100,175,150,25);
						TransingPage.add(amountText);
						
						Label content=new Label("content : ");
						content.setAlignment(1);
						content.setBounds(0, 225, 100, 25);
						TransingPage.add(content);
						contentText=new JTextField();
						contentText.setBounds(100,225,150,25);
						TransingPage.add(contentText);
						
						JButton confirm=new JButton("确认");
						confirm.setBounds(100, 275, 100, 25);
						confirm.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String from_=fromText.getText();
								String to_=toText.getText();
								String amount_=amountText.getText();
								String content_=contentText.getText();
								TransactionService tranService = applicationContext.getBean(TransactionService.class);
								String str2=tranService.sendPostTransaction("deal","[\""+from_+"\",\""+to_+"\",\""+amount_+"\",\""+content_+"\"]");
								if(str2!=null) {
									Label result=new Label(str2);
									result.setForeground(Color.red);
									result.setBounds(0, 325, 300, 25);
									result.setAlignment(1);
									TransingPage.add(result);
								}
							}
						});
						TransingPage.add(confirm);
						TransingPage.setVisible(true);
					}
				});
				TransPage.add(transbutton);
				//转让
				JButton zhuanrangbutton=new JButton("转让账单");
				zhuanrangbutton.setBounds(100, 150, 100, 50);
				zhuanrangbutton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						TransingPage=new JFrame("账单转让");
						TransingPage.setLayout(null);
						TransingPage.setSize(300, 400);
						
						Label title=new Label("账单转让界面");
						title.setAlignment(1);
						title.setBounds(0, 0, 300, 50);
						TransingPage.add(title);
						
						Label receipt_num=new Label("receipt_num : ");
						receipt_num.setAlignment(1);
						receipt_num.setBounds(0, 75, 100, 25);
						TransingPage.add(receipt_num);
						receiptNumText=new JTextField();
						receiptNumText.setBounds(100,75,150,25);
						TransingPage.add(receiptNumText);
						
						Label from=new Label("from : ");
						from.setAlignment(1);
						from.setBounds(0, 125, 100, 25);
						TransingPage.add(from);
						fromText=new JTextField();
						fromText.setBounds(100,125,150,25);
						TransingPage.add(fromText);
						
						Label to=new Label("to : ");
						to.setAlignment(1);
						to.setBounds(0, 175, 100, 25);
						TransingPage.add(to);
						toText=new JTextField();
						toText.setBounds(100,175,150,25);
						TransingPage.add(toText);
						
						Label amount=new Label("amount : ");
						amount.setAlignment(1);
						amount.setBounds(0, 225, 100, 25);
						TransingPage.add(amount);
						amountText=new JTextField();
						amountText.setBounds(100,225,150,25);
						TransingPage.add(amountText);
						
						JButton confirm=new JButton("确认");
						confirm.setBounds(100, 275, 100, 25);
						confirm.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String receipt_num=receiptNumText.getText();
								String from_=fromText.getText();
								String to_=toText.getText();
								String amount_=amountText.getText();
								
								TransactionService tranService = applicationContext.getBean(TransactionService.class);
								String str2=tranService.sendPostTransaction("receipt_transform","[\""+receipt_num+"\",\""+from_+"\",\""+to_+"\",\""+amount_+"\"]");
								if(str2!=null) {
									Label result=new Label(str2);
									result.setForeground(Color.red);
									result.setBounds(0, 325, 300, 25);
									result.setAlignment(1);
									TransingPage.add(result);
								}
							}
							
						});
						TransingPage.add(confirm);
						
						TransingPage.setVisible(true);
					}
					
				});
				TransPage.add(zhuanrangbutton);
				//支付
				JButton payButton=new JButton("支付账单");
				payButton.setBounds(100, 250, 100, 50);
				payButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						TransingPage=new JFrame("支付账单");
						TransingPage.setLayout(null);
						TransingPage.setSize(300, 250);
						
						Label title=new Label("账单支付界面");
						title.setAlignment(1);
						title.setBounds(0, 0, 300, 50);
						TransingPage.add(title);
						
						Label receipt_num=new Label("receipt_num : ");
						receipt_num.setAlignment(1);
						receipt_num.setBounds(0, 75, 100, 25);
						TransingPage.add(receipt_num);
						receiptNumText=new JTextField();
						receiptNumText.setBounds(100,75,150,25);
						TransingPage.add(receiptNumText);
						
						Label amount=new Label("amount : ");
						amount.setAlignment(1);
						amount.setBounds(0, 125, 100, 25);
						TransingPage.add(amount);
						amountText=new JTextField();
						amountText.setBounds(100,125,150,25);
						TransingPage.add(amountText);
						
						JButton confirm=new JButton("确认");
						confirm.setBounds(100, 175, 100, 25);
						confirm.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String receipt_num=receiptNumText.getText();
								String amount=amountText.getText();
								TransactionService tranService = applicationContext.getBean(TransactionService.class);
								String str2=tranService.sendPostTransaction("pay","[\""+receipt_num+"\",\""+amount+"\"]");
								if(str2!=null) {
									Label result=new Label(str2);
									result.setForeground(Color.red);
									result.setBounds(0, 225, 300, 25);
									result.setAlignment(1);
									TransingPage.add(result);
								}
							}
							
						});
						TransingPage.add(confirm);
						
						TransingPage.setVisible(true);
					}
					
				});
				TransPage.add(payButton);
				//查看
				JButton queryButton=new JButton("查看账单");
				queryButton.setBounds(100, 350, 100, 50);
				queryButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						TransingPage=new JFrame("账单查看");
						TransingPage.setLayout(null);
						TransingPage.setSize(300, 450);
						
						Label title=new Label("账单查看界面");
						title.setAlignment(1);
						title.setBounds(0, 0, 300, 50);
						TransingPage.add(title);
						
						Label receipt_num=new Label("receipt_num : ");
						receipt_num.setAlignment(1);
						receipt_num.setBounds(0, 75, 100, 25);
						TransingPage.add(receipt_num);
						receiptNumText=new JTextField();
						receiptNumText.setBounds(100,75,150,25);
						TransingPage.add(receiptNumText);
						
						JButton confirm=new JButton("确认");
						confirm.setBounds(100, 125, 100, 25);
						confirm.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								String str1=receiptNumText.getText();
								TransactionService tranService = applicationContext.getBean(TransactionService.class);
								String str2=tranService.sendGetTransaction("print_receipt","[\""+str1+"\"]");
								if(str2!=null) {
									
									List<Object> str4=JSONArray.parseArray(str2);
									
									fromText.setText(str4.get(0).toString());
									System.out.print(str2);
									toText.setText(str4.get(1).toString());
									amountText.setText(str4.get(2).toString());
									statusText.setText(str4.get(3).toString());
									contentText.setText(str4.get(4).toString());
							}
							}
						});
						TransingPage.add(confirm);
						
						Label from=new Label("from : ");
						from.setAlignment(1);
						from.setBounds(0, 175, 100, 25);
						TransingPage.add(from);
						fromText=new JTextField();
						fromText.setBounds(100,175,150,25);
						TransingPage.add(fromText);
						
						Label to=new Label("to : ");
						to.setAlignment(1);
						to.setBounds(0, 225, 100, 25);
						TransingPage.add(to);
						toText=new JTextField();
						toText.setBounds(100,225,150,25);
						TransingPage.add(toText);
						
						Label amount=new Label("amount : ");
						amount.setAlignment(1);
						amount.setBounds(0, 275, 100, 25);
						TransingPage.add(amount);
						amountText=new JTextField();
						amountText.setBounds(100,275,150,25);
						TransingPage.add(amountText);
						
						Label content=new Label("content : ");
						content.setAlignment(1);
						content.setBounds(0, 325, 100, 25);
						TransingPage.add(content);
						contentText=new JTextField();
						contentText.setBounds(100,325,150,25);
						TransingPage.add(contentText);
						
						Label status=new Label("status : ");
						status.setAlignment(1);
						status.setBounds(0, 375, 100, 25);
						TransingPage.add(status);
						statusText=new JTextField();
						statusText.setBounds(100,375,150,25);
						TransingPage.add(statusText);
						
						TransingPage.setVisible(true);
					}
					
				});
				TransPage.add(queryButton);
				
				TransPage.setVisible(true);
			}
			
		});
		companypage.add(trans);
		
		companypage.setVisible(true);
	}
	public void bankPage() {
		JFrame bankpage=new JFrame("Bank");
		bankpage.setLayout(null);
		bankpage.setSize(200, 250);
		
		JButton register=new JButton("银行注册");
		register.setSize(100, 50);
		register.setLocation(50,50);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Bankregister=new JFrame("CompanyRegister");
				Bankregister.setLayout(null);
				Bankregister.setSize(300,300);
				
				Label label2=new Label("Please input your bank name");
				label2.setBounds(0,20,300,50);
				label2.setAlignment(1);
				Bankregister.add(label2);
			
				nametext= new JTextField();
				nametext.setBounds(75, 100, 150, 25);
				Bankregister.add(nametext);
				
				Label name=new Label("name");
				name.setBounds(10, 100, 55, 25);
				name.setAlignment(2);
				Bankregister.add(name);
				
				JButton register=new JButton("注册");
				register.setBounds(100, 150, 100, 25);
				register.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String str1=nametext.getText();
						TransactionService tranService = applicationContext.getBean(TransactionService.class);
						String str2=tranService.sendPostTransaction("bank_register","[\""+str1+"\"]");
						if(str2!=null) {
							Label result=new Label(str2);
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							Bankregister.add(result);
						}else {
							Label result=new Label("failed");
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							Bankregister.add(result);
						}
					}
				});
				Bankregister.add(register);
				
				Bankregister.setVisible(true);
				}
		});
		bankpage.add(register);
		
		JButton query=new JButton("账单保证");
		query.setSize(100,50);
		query.setLocation(50, 100);
		query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				checkPage=new JFrame("账单保证");
				checkPage.setLayout(null);
				checkPage.setSize(300, 350);
				
				Label title=new Label("账单保证界面");
				title.setAlignment(1);
				title.setBounds(0, 0, 300, 50);
				checkPage.add(title);
				
				Label receipt_num=new Label("receipt_num : ");
				receipt_num.setAlignment(1);
				receipt_num.setBounds(0, 75, 100, 25);
				checkPage.add(receipt_num);
				receiptNumText=new JTextField();
				receiptNumText.setBounds(100,75,150,25);
				checkPage.add(receiptNumText);
				
				Label bank_num=new Label("bank_num : ");
				bank_num.setAlignment(1);
				bank_num.setBounds(0, 125, 100, 25);
				checkPage.add(bank_num);
				bankNumText=new JTextField();
				bankNumText.setBounds(100,125,150,25);
				checkPage.add(bankNumText);
				
				JButton confirm=new JButton("确认");
				confirm.setBounds(100, 175, 100, 25);
				confirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String receipt_num=receiptNumText.getText();
						String bank_num=bankNumText.getText();
						TransactionService tranService = applicationContext.getBean(TransactionService.class);
						String str2=tranService.sendPostTransaction("check","[\""+receipt_num+"\",\""+bank_num+"\"]");
						if(str2!=null) {
							Label result=new Label(str2);
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							checkPage.add(result);
						}else {
							Label result=new Label("failed");
							result.setForeground(Color.red);
							result.setBounds(0, 200, 300, 25);
							result.setAlignment(1);
							checkPage.add(result);
						}
					}
					
				});
				checkPage.add(confirm);
				
				checkPage.setVisible(true);
			}
			
		});
		bankpage.add(query);
		
		JButton financing=new JButton("账单融资");
		financing.setSize(100,50);
		financing.setLocation(50, 150);
		financing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FinancingPage=new JFrame("账单融资");
				FinancingPage.setLayout(null);
				FinancingPage.setSize(300, 450);
				Label title=new Label("账单融资界面");
				title.setAlignment(1);
				title.setBounds(0, 0, 300, 50);
				FinancingPage.add(title);
				Label bank_num=new Label("bank_num : ");
				bank_num.setAlignment(1);
				bank_num.setBounds(0, 75, 100, 25);
				FinancingPage.add(bank_num);
				bankNumText=new JTextField();
				bankNumText.setBounds(100,75,150,25);
				FinancingPage.add(bankNumText);
				
				Label receipt_num=new Label("receipt_num : ");
				receipt_num.setAlignment(1);
				receipt_num.setBounds(0, 125, 100, 25);
				FinancingPage.add(receipt_num);
				receiptNumText=new JTextField();
				receiptNumText.setBounds(100,125,150,25);
				FinancingPage.add(receiptNumText);
				
				Label from=new Label("from : ");
				from.setAlignment(1);
				from.setBounds(0, 175, 100, 25);
				FinancingPage.add(from);
				fromText=new JTextField();
				fromText.setBounds(100,175,150,25);
				FinancingPage.add(fromText);
				
				Label amount=new Label("amount : ");
				amount.setAlignment(1);
				amount.setBounds(0, 225, 100, 25);
				FinancingPage.add(amount);
				amountText=new JTextField();
				amountText.setBounds(100,225,150,25);
				FinancingPage.add(amountText);
				
				JButton confirm=new JButton("确认");
				confirm.setBounds(100, 275, 100, 25);
				confirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String bank_num=bankNumText.getText();
						String receipt_num=receiptNumText.getText();
						String from=fromText.getText();
						String amount=amountText.getText();
						
						TransactionService tranService = applicationContext.getBean(TransactionService.class);
						String str2=tranService.sendPostTransaction("financing","[\""+bank_num+"\",\""+receipt_num+"\",\""+from+"\",\""+amount+"\"]");
						if(str2!=null) {
							Label result=new Label(str2);
							result.setForeground(Color.red);
							result.setBounds(0, 325, 300, 25);
							result.setAlignment(1);
							FinancingPage.add(result);
							
						}
					}
					
				});
				FinancingPage.add(confirm);
				
				FinancingPage.setVisible(true);
			}
		});
		bankpage.add(financing);
		bankpage.setVisible(true);
	}
}
