package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dao.DiaChi_DAO;
import dao.KhachHang_DAO;
import dao.TaiKhoan_DAO;
import entity.DiaChi;
import entity.KhachHang;

public class DialogSuaKhachHang extends JDialog implements ActionListener{
	private KhachHang_DAO kh_DAO;
	private KhachHang khachHang;
	private JPanel wrapper;
	private JTextField sDTTextField;
	private JTextField emailTextField;
	private DefaultComboBoxModel<String> tinhThanhComboBoxModel;
	private JComboBox<String> tinhThanhComboBox;
	private DefaultComboBoxModel<String> quanHuyenComboBoxModel;
	private JComboBox<String> quanHuyenComboBox;
	private DefaultComboBoxModel<String> phuongXaComboBoxModel;
	private JComboBox<String> phuongXaComboBox;
	private JPasswordField nhapLaiMKField;
	private JDateChooser ngaySinhChooser;
	private JTextField hoTenTextField;
	private JLabel maLabel2;
	private JCheckBox gioiTinhCheckBox;
	private JButton huyButton;
	private JButton lamMoiButton;
	private JButton luuButton;
	private JButton doiMKButton;
	public DialogSuaKhachHang(String maKhachHang) {
		setModal(true);
		kh_DAO = new KhachHang_DAO();
		setSize(800, 530);
		khachHang = kh_DAO.getKhachHang(maKhachHang);
		setLocationRelativeTo(null);
		buidDialogSuaKhachHang();
	}
	private void buidDialogSuaKhachHang() {
		
		wrapper = new JPanel();
		wrapper.setLayout(null);
		wrapper.setBackground(MainScreen.BACKGROUND_COLOR);
		
		
		
		JPanel header = new JPanel();
		header.setBackground(MainScreen.HEADER_COLOR);
		header.setBounds(0, 0, 800, 80);
		header.setLayout(null);
		wrapper.add(header);
		
		JLabel tenCongTy = new JLabel("TOP TRAVEL", JLabel.CENTER);
		tenCongTy.setForeground(Color.WHITE);
		tenCongTy.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 46));
		tenCongTy.setBounds(0, 0, 800, 40);
		header.add(tenCongTy);
		
		JLabel title = new JLabel("S???a kh??ch h??ng", JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 18));
		title.setBounds(0, 50, 800, 30);
		header.add(title);
		
		JLabel maLabel1 = new JLabel("M?? kh??ch h??ng: ");
		maLabel1.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel1.setBounds(10, 85, 130, 30);
		wrapper.add(maLabel1);
		
		maLabel2 = new JLabel();
		maLabel2.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel2.setBounds(140, 85, 100, 30);
		wrapper.add(maLabel2);
		
		JLabel gioiTinhLabel = new JLabel("Gi???i t??nh: ");
		gioiTinhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhLabel.setBounds(500, 85, 100, 30);
		wrapper.add(gioiTinhLabel);
		
		gioiTinhCheckBox = new JCheckBox("N???");
		gioiTinhCheckBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		gioiTinhCheckBox.setBounds(580, 85, 300, 30);
		wrapper.add(gioiTinhCheckBox);
		
		JLabel hoTenLabel = new JLabel("H??? t??n (*): ");
		hoTenLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenLabel.setBounds(10, 135, 100, 30);
		wrapper.add(hoTenLabel);

		hoTenTextField = new JTextField();
		hoTenTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		hoTenTextField.setBounds(140, 135, 640, 30);
		wrapper.add(hoTenTextField);

		

		JLabel ngaySinhLabel = new JLabel("Ng??y sinh (*): ");
		ngaySinhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngaySinhLabel.setBounds(10, 185, 100, 30);
		wrapper.add(ngaySinhLabel);

		String dd = LocalDate.now().toString();

		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
			ngaySinhChooser = new JDateChooser();
			ngaySinhChooser.setBounds(140, 185, 640, 30);
			ngaySinhChooser.setDate(date);
			ngaySinhChooser.setDateFormatString("dd/MM/yyyy");
			ngaySinhChooser.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
			ngaySinhChooser.setBackground(MainScreen.BACKGROUND_COLOR);
			wrapper.add(ngaySinhChooser);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel sDTLabel = new JLabel("S??? ??i???n tho???i (*):");
		sDTLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTLabel.setBounds(10, 235, 120, 30);
		wrapper.add(sDTLabel);

		sDTTextField = new JTextField();
		sDTTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		sDTTextField.setBounds(140, 235, 640, 30);
		wrapper.add(sDTTextField);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailLabel.setBounds(10, 285, 100, 30);
		wrapper.add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		emailTextField.setBounds(140, 285, 640, 30);
		wrapper.add(emailTextField);

		JLabel diaChiLabel = new JLabel("?????a ch??? (*): ");
		diaChiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		diaChiLabel.setBounds(10, 335, 100, 30);
		wrapper.add(diaChiLabel);

		tinhThanhComboBoxModel = new DefaultComboBoxModel<String>();
		tinhThanhComboBoxModel.addAll(DiaChi_DAO.getAllTinh());
		tinhThanhComboBox = new JComboBox<String>(tinhThanhComboBoxModel);
		tinhThanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		
		tinhThanhComboBox.setBounds(140, 335, 200, 30);
		wrapper.add(tinhThanhComboBox);

		quanHuyenComboBoxModel = new DefaultComboBoxModel<String>();
		quanHuyenComboBox = new JComboBox<String>(quanHuyenComboBoxModel);
		quanHuyenComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		quanHuyenComboBox.setBounds(360, 335, 200, 30);
		wrapper.add(quanHuyenComboBox);

		phuongXaComboBoxModel = new DefaultComboBoxModel<String>();
		phuongXaComboBox = new JComboBox<String>(phuongXaComboBoxModel);
		phuongXaComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		phuongXaComboBox.setBounds(580, 335, 200, 30);
		wrapper.add(phuongXaComboBox);

		doiMKButton = new JButton("?????i m???t kh???u");
		doiMKButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		doiMKButton.setBounds(10, 395, 140, 40);
		wrapper.add(doiMKButton);
		
		nhapLaiMKField = new JPasswordField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(getText().length() > 0) {
					return;
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font(MainScreen.FONT_TEXT, Font.ITALIC, 16));
				g2d.drawString("Nh???p m???t kh???u", getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
			}
		};
		nhapLaiMKField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		nhapLaiMKField.setBounds(160, 400, 200, 30);
		nhapLaiMKField.setVisible(false);
		wrapper.add(nhapLaiMKField);
		
		huyButton = new JButton("H???y");
		huyButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		huyButton.setBounds(10, 445, 100, 40);
		wrapper.add(huyButton);
		
		lamMoiButton = new JButton("L??m m???i");
		lamMoiButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		lamMoiButton.setBounds(560, 445, 100, 40);
		wrapper.add(lamMoiButton);
		
		luuButton = new JButton("L??u");
		luuButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		luuButton.setBounds(670, 445, 100, 40);
		wrapper.add(luuButton);
		
		setContentPane(wrapper);
		themDuLieuMacDinh();
		
		tinhThanhComboBox.addActionListener(this);
		quanHuyenComboBox.addActionListener(this);
		doiMKButton.addActionListener(this);
		huyButton.addActionListener(this);
		lamMoiButton.addActionListener(this);
		luuButton.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tinhThanhComboBox)) {
			quanHuyenComboBoxModel.removeAllElements();
			quanHuyenComboBoxModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(tinhThanhComboBox.getSelectedItem().toString()));
			quanHuyenComboBox.setSelectedIndex(0);
		}
		else if (o.equals(quanHuyenComboBox)) {
			if(quanHuyenComboBoxModel.getSelectedItem() != null) {
				phuongXaComboBoxModel.removeAllElements();
				phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString()));
				phuongXaComboBox.setSelectedIndex(0);
			}
		}
		else if (o.equals(doiMKButton)) {
			if(nhapLaiMKField.isVisible())
				nhapLaiMKField.setVisible(false);
			else
				nhapLaiMKField.setVisible(true);
		}
		else if (o.equals(huyButton)) {
			dispose();
		}
		else if(o.equals(lamMoiButton)) {
			themDuLieuMacDinh();
		}
		else if(o.equals(luuButton)) {
			if(checkData_SuaKhachHang()) {
				String maKhachHang = maLabel2.getText();
				String tenKhachHang = hoTenTextField.getText().trim();
				boolean gioiTinh = gioiTinhCheckBox.isSelected();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date ngaySinh = java.sql.Date.valueOf(sdf.format(ngaySinhChooser.getDate()));
				String soDienThoai = sDTTextField.getText().trim();
				if(!soDienThoai.equals(khachHang.getSoDienThoai())) {
					if(TaiKhoan_DAO.getTaiKhoan(soDienThoai) != null) {
						JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i ???? ???????c ????ng k??!");
						selectAllText();
						sDTTextField.requestFocus();
						return;
					}
				}
				String email = emailTextField.getText().trim();
				DiaChi diaChi = DiaChi_DAO.getDiaChi(tinhThanhComboBox.getSelectedItem().toString(), quanHuyenComboBox.getSelectedItem().toString(), phuongXaComboBox.getSelectedItem().toString());
				String matKhau = nhapLaiMKField.getText();
				KhachHang khachHang = new KhachHang(soDienThoai, email, matKhau, maKhachHang, tenKhachHang, gioiTinh, ngaySinh, diaChi);
				if(KhachHang_DAO.updateKhachHang(khachHang, this.khachHang.getSoDienThoai())) {
					JOptionPane.showMessageDialog(this, "S???a th??nh c??ng!");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng");
					selectAllText();
					hoTenTextField.requestFocus();
				}
			}
		}
	}
	
	private void themDuLieuMacDinh() {
		maLabel2.setText(khachHang.getMaKhachHang());
		gioiTinhCheckBox.setSelected(khachHang.isGioiTinh());
		hoTenTextField.setText(khachHang.getTenKhachHang());
		ngaySinhChooser.setDate(khachHang.getNgaySinh());
		sDTTextField.setText(khachHang.getSoDienThoai());
		emailTextField.setText(khachHang.getEmail());
		tinhThanhComboBox.setSelectedItem(khachHang.getDiaChi().getTinhThanh());
		quanHuyenComboBoxModel.addAll(DiaChi_DAO.getQuanHuyenTheoTinh(khachHang.getDiaChi().getTinhThanh()));
		phuongXaComboBoxModel.addAll(DiaChi_DAO.getPhuongXaTheoQuanHuyenVaTinh(khachHang.getDiaChi().getTinhThanh(), khachHang.getDiaChi().getQuanHuyen()));
		quanHuyenComboBox.setSelectedItem(khachHang.getDiaChi().getQuanHuyen());
		phuongXaComboBox.setSelectedItem(khachHang.getDiaChi().getPhuongXa());
		hoTenTextField.requestFocus();
	}
	private void selectAllText() {
		sDTTextField.selectAll();
		hoTenTextField.selectAll();
		emailTextField.selectAll();
		if(nhapLaiMKField.isVisible()) {
			nhapLaiMKField.setText("");
		}
	}
	private void getShowMessage(String str, JTextField txt)
	{
		JOptionPane.showMessageDialog(this , str);
		txt.selectAll();
		txt.requestFocus();
	}
	//

	private boolean checkData_SuaKhachHang()
	{
		String mess = "";
		String tenKhachHang = hoTenTextField.getText().trim();
		if(!(tenKhachHang.length()>0 && tenKhachHang.matches("^([???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????A-Z]{1}[???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????a-z]*\\s)+([???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????A-Z]{1}[???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????a-z]*)$")))
		{
			if (tenKhachHang.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "H??y nh???p t??n kh??ch h??ng.");
			}
			else {
				JOptionPane.showMessageDialog(this, "T??n kh??ch h??ng ph???i ???????c ?????nh d???ng VD: Nguy???n V??n A");				
			}
			hoTenTextField.selectAll();
			hoTenTextField.requestFocus();
			return false;
		}
		//
		
		String soDienThoai = sDTTextField.getText().trim();
		if (!(soDienThoai.length()>0 && soDienThoai.matches("^0[0-9]{9}$"))) {
			if (soDienThoai.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "H??y nh???p s??? ??i???n tho???i c???a kh??ch h??ng.");
			}
			else {
				JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i c?? 10 s??? v?? b???t ?????u b???ng s??? 0.");				
			}
			sDTTextField.selectAll();
			sDTTextField.requestFocus();
			return false;
		}
		
		//
		String email = emailTextField.getText().trim();
		if (!(email.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[a-z]{2,4}$"))) {
			if (email.length() == 0) {
				mess = "";
			} else {
			mess = "Email phai ????ng theo ?????nh d???ng (VD: Abc@gmail.com)";
			getShowMessage(mess,emailTextField);
			return false;
			}				
		}
		//
		
		if (tinhThanhComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this , "Hay ch???n ?????a ch???.");
			return false;
		}
		//
		if(nhapLaiMKField.isVisible()) {
			String matKhau = nhapLaiMKField.getText().trim();
			if (!(matKhau.length()>0 && matKhau.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_=+@$!%*#?&])[A-Za-z\\d-_=+@$!%*#?&]{6,20}$"))) {
				if (matKhau.length() <= 0) {
					mess = "H??y nh???p th??ng tin cho ?? m???t kh???u.";
				}
				else {
					mess = "M???t kh???u ph???i tr??n 6 k?? t??? trong d?? ph???i c?? m???t ch??? s???, m???t ch??? c??i th?????ng, m???t ch??? hoa v?? m???t k?? t??? ?????c bi???t";
				}
				getShowMessage(mess, nhapLaiMKField);
				return false;
			}
		}
		return true;
	}
}
