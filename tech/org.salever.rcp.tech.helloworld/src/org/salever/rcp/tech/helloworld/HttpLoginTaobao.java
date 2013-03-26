package org.salever.rcp.tech.helloworld;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class implements a web browser
 */
public class HttpLoginTaobao {
	
	private static final int STEP1_STATE = 0x01;
	private static final int STEP2_STATE = 0x02;
	private static final int STEP3_STATE = 0x03;
	private static final int STEP4_STATE = 0x04;
	
	private int state = 0;
	
	private Browser browser;
	// private Browser browser2;
	// private Browser browser3;
	// private Browser browser4;
	public final static String USER_DIR = System.getProperty("user.dir")
			+ File.separatorChar;

	/**
	 * Runs the application
	 * 
	 * the initial location to display
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("淘宝代付款");
		createContents(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param shell
	 *            the main window
	 * @param location
	 *            the initial location
	 */
	public void createContents(Shell shell) {
		shell.setLayout(new FormLayout());

		Composite controls = new Composite(shell, SWT.NONE);
		FormData data = new FormData();
		browser = new Browser(shell, SWT.BORDER);
		data.top = new FormAttachment(controls);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		browser.setLayoutData(data);
		controls.setLayout(new GridLayout(0, false));
		
		browser.addProgressListener(new Step1ProgressListener());
		browser.addProgressListener(new Step2ProgressListener());
		browser.addProgressListener(new Step3ProgressListener());
		browser.addProgressListener(new Step4ProgressListener());
		state = STEP1_STATE;
		// 第一步：登录淘宝
		browser.setUrl("https://login.taobao.com/member/login.jhtml?redirect_url=http%3A%2F%2Fwww.taobao.com%2F");
		
		
//		browser.addProgressListener(new ProgressAdapter() {
//			public void completed(ProgressEvent event) {
//				// System.out.println(browser.getUrl());
//				if (browser.getUrl().startsWith("https://")) {
//					// browser.execute("alert(document.documentElement.outerHTML)");
//					// //可以得到页面源文件
//					
//					browser.execute(loginPageScript());
//					
////					doStep2();
//
//					// browser.execute(getGotoScript());
//				}
//			}
//		});

//		// 第二步：访问代付款链接
//		browser2 = new Browser(shell, SWT.BORDER);
//		browser2.setLayoutData(data);
//		controls.setLayout(new GridLayout(0, false));
//
//		// 第三步：举个例子，第二步没完成，就已经执行第三步了
//		browser4 = new Browser(shell, SWT.BORDER);
//		browser4.setLayoutData(data);
//		controls.setLayout(new GridLayout(0, false));
//		browser4.setUrl("https://passport.baidu.com/?reg&tpl=mn");
//		browser4.addProgressListener(new ProgressAdapter() {
//			public void completed(ProgressEvent event) {
//				// System.out.println(browser.getUrl());
//				if (browser4.getUrl().startsWith("https://")) {
//					browser.execute(gotoConfirmStep1());
//				}
//			}
//		});
	}

	protected void doStep2() {
		System.out.println("do step 2");
		
		browser.execute(gotoPayForAnother());
	
//		browser.addProgressListener(new ProgressAdapter() {
//			public void completed(ProgressEvent event) {
//				// System.out.println(browser.getUrl());
//				if (browser.getUrl().startsWith("https://")) {
//					browser.execute(gotoPayForAnother());
////					doStep3();
//				}
//			}
//		});

	}

	protected void doStep3() {
		 System.out.println("do step 3");
		browser.setUrl("https://passport.baidu.com/?reg&tpl=mn");
		browser.execute(gotoConfirmStep1());
//		browser.addProgressListener(new ProgressAdapter() {
//			public void completed(ProgressEvent event) {
//				// System.out.println(browser.getUrl());
//				if (browser.getUrl().startsWith("https://")) {
//					browser.execute(gotoConfirmStep1());
//					doStep4();
//				}
//			}
//		});

	}

	protected void doStep4() {
		 System.out.println("do step 4");
		browser.close();

	}

	private String loginPageScript() {
		// 内部执行script
		String scriptContent = "var inp_unameObj=document.getElementById('TPL_username_1');"
				+ "if(inp_unameObj!=null) inp_unameObj.value='慕率两';"
				+ "var passwordObj=document.getElementById('TPL_password');"
				+ "if(passwordObj!=null) passwordObj.value='q12345';"
				+ "var form = document.getElementById('J_StaticForm'); form.submit();";
		return scriptContent;
	}

	private String gotoPayForAnother() {
		String script = "window.location.href = 'http://trade.taobao.com/trade/pay.htm?biz_order_id=63601298052309&biz_type=200&ispayforanother=true'";
		return script;
	}

	private String gotoConfirmStep1() {
		// 内部执行script
		String scriptContent = "window.location.href = 'https://auth.alipay.com/login/index.htm'";
		return scriptContent;
	}

	public static void main(String[] args) {
		new HttpLoginTaobao().run();
	}
	
	class Step1ProgressListener extends ProgressAdapter{
		@Override
		public void completed(ProgressEvent event) {
			if(state == STEP1_STATE){
				state++;
				doStep1();
			
			}
		
		}
	}
	
	class Step2ProgressListener extends ProgressAdapter{
		@Override
		public void completed(ProgressEvent event) {
			if(state == STEP2_STATE){
				state++;
				doStep2();
			}
		}
	}
	
	class Step3ProgressListener extends ProgressAdapter{
		@Override
		public void completed(ProgressEvent event) {
			if(state == STEP3_STATE){
				state++;
				doStep3();
			}
		}
	}
	
	class Step4ProgressListener extends ProgressAdapter{
		@Override
		public void completed(ProgressEvent event) {
			if(state == STEP4_STATE){
				state++;
				doStep4();
			}
		}
	}

	public void doStep1() {
		browser.execute(loginPageScript());

	}
}

