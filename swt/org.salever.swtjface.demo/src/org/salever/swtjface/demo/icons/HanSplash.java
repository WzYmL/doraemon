package org.salever.swtjface.demo.icons;

import org.eclipse.swt.SWT;

import org.eclipse.swt.graphics.Font;

import org.eclipse.swt.graphics.Image;

import org.eclipse.swt.graphics.ImageData;

import org.eclipse.swt.graphics.Rectangle;

import org.eclipse.swt.layout.FormAttachment;

import org.eclipse.swt.layout.FormData;

import org.eclipse.swt.layout.FormLayout;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.ProgressBar;

import org.eclipse.swt.widgets.Shell;

 

public class HanSplash {

 

   private Shell sShell=null;

   private Image image=null;

   private Label TextLabel=null;

   private ProgressBar bar=null;

 

   public static void main(String[] args) {

      Display display = Display.getDefault();

      HanSplash thisClass =new HanSplash();

      thisClass.createSShell();

      thisClass.sShell.open();

 

      while(!thisClass.sShell.isDisposed()) {

          if(!display.readAndDispatch())

             display.sleep();

      }

      display.dispose();

    

   }

  
 

   private void createSShell() {

      sShell=new Shell(SWT.NO_TRIM|SWT.ON_TOP);//shell的风格设为没有标题栏并且居于窗口最上

      sShell.setLayout(new FormLayout());

      sShell.setText("Shell");

      image=new Image(Display.getCurrent(),"splash.gif");//闪屏的图片

      sShell.setBackgroundImage(image);//将shell的背景设为该图片

      ImageData imdata =image.getImageData();

      sShell.setSize(imdata.width, imdata.height);//将shell的大小设为图片大小

      sShell.setBackgroundMode(SWT.INHERIT_FORCE);//shell的该模式可以将起其上面label变为透明的

      FormLayout layout =new FormLayout();

      sShell.setLayout(layout);//shell的Layout使用FormLayout

      TextLabel=new Label(sShell, SWT.NONE);//在shell上创建一个用来显示程序进度信息的Label

      TextLabel.setFont(new Font(Display.getDefault(),"", 10, SWT.NORMAL));

      bar=new ProgressBar(sShell, SWT.NONE);//在shell上创建一个进度条

      bar.setMaximum(10);

 

      //在FormLayout中设置Label的位置

      FormData TextLabelData =new FormData(100, 20);

      TextLabelData.left=new FormAttachment(0, 0);

      TextLabelData.right=new FormAttachment(100, 0);

      TextLabelData.bottom=new FormAttachment(100, -15);

      TextLabel.setLayoutData(TextLabelData);

 

      //在FormLayout中设置ProgressBar的位置

      FormData progressData =new FormData(100, 15);

      progressData.left=new FormAttachment(0, 0);

      progressData.right=new FormAttachment(100, 0);

      progressData.bottom=new FormAttachment(100, 0);

      bar.setLayoutData(progressData);

 

      //该段代码实现shell在屏幕中的居中

      Rectangle splashRect =sShell.getBounds();

      Rectangle displayRect = Display.getCurrent().getBounds();

      int x = (displayRect.width- splashRect.width) / 2;

      int y = (displayRect.height- splashRect.height) / 2;

      sShell.setLocation(x, y);

 

      //设置进度条的事件处理

      Display.getDefault().asyncExec(new Runnable() {

          public void run() {

             for(int i = 1; i <= 10; i++) {

                 TextLabel.setText("任务已完成"+i +"0%");//打印程序进度信息

                 TextLabel.update();

                 bar.setSelection(i);//显示进度条进度信息

                 try{

                    Thread.sleep(1000);

                 }catch(Throwable e) {

                 }

             }

             sShell.close();

             image.dispose();

          }

      });

   }

}