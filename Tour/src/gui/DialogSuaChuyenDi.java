package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.EventObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import dao.ChuyenDi_DAO;
import dao.DiaChi_DAO;
import dao.HuongDanVien_DAO;
import dao.LoaiChuyenDi_DAO;
import entity.ChuyenDi;

public class DialogSuaChuyenDi extends JDialog implements ActionListener{
	private JPanel wrapper;
	private JLabel maLabel2;
	private ChuyenDi_DAO cd_DAO;
	private JDateChooser ngayKhoiHanhDateChooser;
	private JDateChooser ngayKetThucDateChooser;
	private JButton huyButton;
	private JButton lamMoiButton;
	private JButton luuButton;
	private JButton themHuongDanVien;
	private JTable tableHuongDanVien;
	private DefaultTableModel tableModel;
	private JComboBox<String> noiKhoiHanhComboBox;
	private JTextField giaChuyenDiField;
	private JSpinner soChoSpinner;
	private JTextArea motaArea;
	private JComboBox<String> loaiChuyenDiboComboBox;
	private ChuyenDi chuyenDi;
	public DialogSuaChuyenDi(String maChuyenDi) {
		setModal(true);
		cd_DAO = new ChuyenDi_DAO();
		chuyenDi = ChuyenDi_DAO.getChuyenDi(maChuyenDi);
		setSize(720, 550);
		setLocationRelativeTo(null);
		buidDialogSuaChuyenDi();
	}
	private void buidDialogSuaChuyenDi() {
		
		wrapper = new JPanel();
		wrapper.setLayout(null);
		wrapper.setBackground(MainScreen.BACKGROUND_COLOR);
		
		JPanel header = new JPanel();
		header.setBackground(MainScreen.HEADER_COLOR);
		header.setBounds(0, 0, getWidth(), 80);
		header.setLayout(null);
		wrapper.add(header);
		
		JLabel tenCongTy = new JLabel("TOP TRAVEL", JLabel.CENTER);
		tenCongTy.setForeground(Color.WHITE);
		tenCongTy.setFont(new Font(MainScreen.FONT_TEXT, Font.BOLD, 46));
		tenCongTy.setBounds(0, 0, getWidth(), 40);
		header.add(tenCongTy);
		
		JLabel title = new JLabel("Th??m chuy???n ??i", JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 18));
		title.setBounds(0, 50, getWidth(), 30);
		header.add(title);
		
		JLabel maLabel1 = new JLabel("Chuy???n ??i:");
		maLabel1.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel1.setBounds(10, 85, 130, 30);
		wrapper.add(maLabel1);
		
		maLabel2 = new JLabel();
		maLabel2.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		maLabel2.setBounds(140, 85, 100, 30);
		wrapper.add(maLabel2);
		
		JLabel loaiChuyenDiLabel = new JLabel("Lo???i chuy???n ??i: ");
	    loaiChuyenDiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    loaiChuyenDiLabel.setBounds(360, 85, 120, 30);
	    wrapper.add(loaiChuyenDiLabel);
	    
	    DefaultComboBoxModel<String> loaiChuyenDiBoxModel = new DefaultComboBoxModel<String>();
	    loaiChuyenDiboComboBox = new JComboBox<String>(loaiChuyenDiBoxModel);
	    loaiChuyenDiBoxModel.addAll(LoaiChuyenDi_DAO.getAllLoaiChuyenDi());
	    loaiChuyenDiboComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    loaiChuyenDiboComboBox.setBounds(490, 85, 200, 30);
	    loaiChuyenDiboComboBox.setSelectedIndex(0);
	    wrapper.add(loaiChuyenDiboComboBox);
		
		JLabel ngayKhoiHanhLabel = new JLabel("Ng??y kh???i h??nh:");
		ngayKhoiHanhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN ,16));
		ngayKhoiHanhLabel.setBounds(10, 135, 130, 30);
		wrapper.add(ngayKhoiHanhLabel);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ngayKhoiHanhDateChooser = new JDateChooser();
		ngayKhoiHanhDateChooser.setBounds(140, 135, 200, 30);
		ngayKhoiHanhDateChooser.setDateFormatString("dd/MM/yyyy");
		ngayKhoiHanhDateChooser.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKhoiHanhDateChooser.setBackground(wrapper.getBackground());
		Calendar maxDate2 = Calendar.getInstance();
		maxDate2.setTime(Date.valueOf(LocalDate.now()));
		maxDate2.roll(Calendar.DAY_OF_YEAR, 3);
		ngayKhoiHanhDateChooser.setMinSelectableDate(Date.valueOf(df.format(maxDate2.getTime())));
		wrapper.add(ngayKhoiHanhDateChooser);
		
		
		JLabel ngayKetThucLabel = new JLabel("Ng??y k???t th??c: ");
		ngayKetThucLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN ,16));
		ngayKetThucLabel.setBounds(360, 135, 130, 30);
		wrapper.add(ngayKetThucLabel);
		
		ngayKetThucDateChooser = new JDateChooser();
		ngayKetThucDateChooser.setBounds(490, 135, 200, 30);
		ngayKetThucDateChooser.setDateFormatString("dd/MM/yyyy");
		ngayKetThucDateChooser.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		ngayKetThucDateChooser.setBackground(wrapper.getBackground());
		wrapper.add(ngayKetThucDateChooser);
		
		JLabel soChoLabel = new JLabel("S??? ch???: ");
		soChoLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		soChoLabel.setBounds(10, 185, 70, 30);
		wrapper.add(soChoLabel);
		
		soChoSpinner = new JSpinner();
		soChoSpinner.setBounds(80, 185, 60, 30);
		wrapper.add(soChoSpinner);
	    
	    JLabel giaChuyenDiLabel = new JLabel("Gi?? (VND): ");
		giaChuyenDiLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		giaChuyenDiLabel.setBounds(150, 185, 90, 30);
		wrapper.add(giaChuyenDiLabel);
		
		giaChuyenDiField = new JTextField();
		giaChuyenDiField.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		giaChuyenDiField.setBounds(240, 185, 90, 30);
		wrapper.add(giaChuyenDiField);
		
	    JLabel noiKhoiHanhLabel = new JLabel("N??i kh???i h??nh: ");
	    noiKhoiHanhLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    noiKhoiHanhLabel.setBounds(360, 185, 120, 30);
	    wrapper.add(noiKhoiHanhLabel);
	    
	    noiKhoiHanhComboBox = new JComboBox<String>();
	    noiKhoiHanhComboBox.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    noiKhoiHanhComboBox.addItem("Th??? ???? H?? N???i");
	    noiKhoiHanhComboBox.addItem("Th??nh ph??? H??? Ch?? Minh");
	    noiKhoiHanhComboBox.addItem("Th??nh ph??? ???? N???ng");
	    noiKhoiHanhComboBox.setBounds(490, 185, 200, 30);
	    noiKhoiHanhComboBox.setSelectedIndex(0);
	    wrapper.add(noiKhoiHanhComboBox);
	    
	    JLabel motaLabel = new JLabel("M?? t???: ");
	    motaLabel.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    motaLabel.setBounds(10, 235, 70, 30);
	    wrapper.add(motaLabel);
	    
	    motaArea = new JTextArea();
	    motaArea.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
	    motaArea.setLineWrap(true);
	    motaArea.setAutoscrolls(true);
	    motaArea.setWrapStyleWord(true);
	    JScrollPane sp = new JScrollPane(motaArea);
	    sp.setBounds(70, 235, 620, 80);
	    wrapper.add(sp);
	    
	    themHuongDanVien = new JButton("H?????ng d???n vi??n");
	    themHuongDanVien.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 12));
	    themHuongDanVien.setBounds(10, 335, 130, 30);
	    wrapper.add(themHuongDanVien);
	    
	    String[] columnTitle = {"ID", "T??n nh??n vi??n", "Gi???i t??nh"};
	    tableModel = new DefaultTableModel(columnTitle, 0);
	    tableHuongDanVien = new JTable(tableModel) {
	    	@Override
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
	    };
	    tableHuongDanVien.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		tableHuongDanVien.getTableHeader().setBackground(MainScreen.COLOR_MAIN);
		tableHuongDanVien.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		tableHuongDanVien.getTableHeader().setPreferredSize(new Dimension(1220, 30));
		tableHuongDanVien.getTableHeader().setResizingAllowed(false);
		tableHuongDanVien.getTableHeader().setReorderingAllowed(false);
		tableHuongDanVien.setFillsViewportHeight(true);
		tableHuongDanVien.setShowGrid(true);
		tableHuongDanVien.setGridColor(MainScreen.BACKGROUND_COLOR);
		tableHuongDanVien.setRowHeight(30);
		tableHuongDanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    JScrollPane huongDanVienJScrollPane = new JScrollPane(tableHuongDanVien, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    huongDanVienJScrollPane.setBounds(10, 375, 680, 70);
	    wrapper.add(huongDanVienJScrollPane);
	    
	    huyButton = new JButton("H???y");
		huyButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		huyButton.setBounds(10, 460, 100, 40);
		wrapper.add(huyButton);
		
		lamMoiButton = new JButton("L??m m???i");
		lamMoiButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		lamMoiButton.setBounds(480, 460, 100, 40);
		wrapper.add(lamMoiButton);
		
		luuButton = new JButton("L??u");
		luuButton.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		luuButton.setBounds(600, 460, 100, 40);
		wrapper.add(luuButton);
	    
	    getContentPane().add(wrapper);
		maLabel2.setText(cd_DAO.phatSinhMaChuyenDi());
		huyButton.addActionListener(this);
		lamMoiButton.addActionListener(this);
		themHuongDanVien.addActionListener(this);
		luuButton.addActionListener(this);
		themDuLieuMacDinh();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(themHuongDanVien)) {
			if(ngayKhoiHanhDateChooser.getDate() != null && ngayKetThucDateChooser.getDate() != null)
				createDialogHuongDanVien();
			else
				JOptionPane.showMessageDialog(this, "Ch???n ng??y kh???i h??nh v?? ng??y k???t th??c tr?????c!");
		} else if (o.equals(huyButton)) {
			dispose();
		}else if (o.equals(luuButton)) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String maChuyenDi = maLabel2.getText();
				String ngayKhoiHanh = df.format(ngayKhoiHanhDateChooser.getDate());
				String ngayKetThuc = df.format(ngayKetThucDateChooser.getDate());
				int soCho = Integer.parseInt(soChoSpinner.getValue().toString());
				double giaChuyenDi = Double.parseDouble(giaChuyenDiField.getText());
				String noiKhoiHanh = noiKhoiHanhComboBox.getSelectedItem().toString();
				if(noiKhoiHanh.equals("Th??? ???? H?? N???i"))
					noiKhoiHanh = "DC-0000622";
				else if(noiKhoiHanh.equals("Th??nh ph??? H??? Ch?? Minh"))
					noiKhoiHanh = "DC-0001012";
				else
					noiKhoiHanh = "DC-0000108";
				String moTa = motaArea.getText();
				String tenLoaiChuyenDi = loaiChuyenDiboComboBox.getSelectedItem().toString();
				String huongDanVien = tableHuongDanVien.getValueAt(0, 0).toString();
				ChuyenDi chuyenDi = new ChuyenDi();
				chuyenDi.setMaChuyenDi(maChuyenDi);
				chuyenDi.setNgayKhoiHanh(Date.valueOf(ngayKhoiHanh));
				chuyenDi.setNgayKetThuc(Date.valueOf(ngayKetThuc));
				chuyenDi.setSoCho(soCho);
				chuyenDi.setGiaChuyenDi(giaChuyenDi);
				chuyenDi.setNoiKhoiHanh(DiaChi_DAO.getDiaChi(noiKhoiHanh));
				chuyenDi.setMoTa(moTa);
				chuyenDi.setLoaiChuyenDi(LoaiChuyenDi_DAO.getLoaiChuyenDiTheoTen(tenLoaiChuyenDi));
				chuyenDi.setHuongDanVien(HuongDanVien_DAO.getHuongDanVien(huongDanVien));
				if(cd_DAO.updateChuyenDi(chuyenDi)) {
					JOptionPane.showMessageDialog(this, "S???a th??nh c??ng!");
					dispose();
				}
				else
					JOptionPane.showMessageDialog(this, "S???a th???t b???i!");
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "?????nh d???ng kh??ng ph?? h???p!");
				e2.printStackTrace();
			} 
		}else if (o.equals(lamMoiButton)) {
			themDuLieuMacDinh();
		}
		
	}
	
	private void createDialogHuongDanVien() {
		HuongDanVien_Pane huongDanVien_Pane = new HuongDanVien_Pane();
		JDialog themHuongDanVienVaoChuyenDi = new JDialog();
		themHuongDanVienVaoChuyenDi.setSize(800, 500);
		themHuongDanVienVaoChuyenDi.setLocationRelativeTo(null);
		themHuongDanVienVaoChuyenDi.setModal(true);
		JPanel main = new JPanel();
		main.setBackground(MainScreen.BACKGROUND_COLOR);
		main.setLayout(null);
		themHuongDanVienVaoChuyenDi.add(main);
		huongDanVien_Pane.getTimKiemTen().setBounds(10, 10, 200, 30);
		main.add(huongDanVien_Pane.getTimKiemTen());
		huongDanVien_Pane.getTimKiemSDT().setBounds(230, 10, 200, 30);
		main.add(huongDanVien_Pane.getTimKiemSDT());
		huongDanVien_Pane.getGioiTinhComboBox().setBounds(450, 10, 100, 30);
		main.add(huongDanVien_Pane.getGioiTinhComboBox());
		huongDanVien_Pane.getTrangThaiComboBox().setBounds(570, 10, 200, 30);
		main.add(huongDanVien_Pane.getTrangThaiComboBox());
		int[] columnWidth = {100, 190, 80, 120, 100, 200};
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = huongDanVien_Pane.getTable().getColumnModel().getColumn(i);
			column.setMinWidth(columnWidth[i]);
			column.setMaxWidth(columnWidth[i]);
			column.setPreferredWidth(columnWidth[i]);
		}
		huongDanVien_Pane.getSpTableDiaDanh().setBounds(10, 50, 770, 320);
		main.add(huongDanVien_Pane.getSpTableDiaDanh());
		huongDanVien_Pane.getButtonDauTrang().setBounds(260, 390, 40, 30);
		main.add(huongDanVien_Pane.getButtonDauTrang());
		huongDanVien_Pane.getButtonTruoc().setBounds(320, 390, 40, 30);
		main.add(huongDanVien_Pane.getButtonTruoc());
		huongDanVien_Pane.getSoTrang().setBounds(380, 390, 40, 30);
		main.add(huongDanVien_Pane.getSoTrang());
		huongDanVien_Pane.getButtonKeTiep().setBounds(440, 390, 40, 30);
		main.add(huongDanVien_Pane.getButtonKeTiep());
		huongDanVien_Pane.getButtonCuoiTrang().setBounds(500, 390, 40, 30);
		main.add(huongDanVien_Pane.getButtonCuoiTrang());
		JButton them = new JButton("Th??m");
		them.setFont(new Font(MainScreen.FONT_TEXT, Font.PLAIN, 16));
		them.setBounds(680, 420, 100, 40);
		them.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(huongDanVien_Pane.getTable().getSelectedRow() != -1) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String maHuongDanVien = huongDanVien_Pane.getTable().getValueAt(huongDanVien_Pane.getTable().getSelectedRow(), 0).toString();
					String tenHuongDanVien = huongDanVien_Pane.getTable().getValueAt(huongDanVien_Pane.getTable().getSelectedRow(), 1).toString();
					String gioiTinh = huongDanVien_Pane.getTable().getValueAt(huongDanVien_Pane.getTable().getSelectedRow(), 2).toString();
					Date ngayKhoiHanh = Date.valueOf(df.format(ngayKhoiHanhDateChooser.getDate()));
					Date ngayKetThuc = Date.valueOf(df.format(ngayKetThucDateChooser.getDate()));
					if(HuongDanVien_DAO.ktLichHuongDanVien(maHuongDanVien, ngayKhoiHanh, ngayKetThuc)) {
						tableModel.getDataVector().removeAllElements();
						tableModel.addRow(new Object[] {maHuongDanVien, tenHuongDanVien, gioiTinh});
						themHuongDanVienVaoChuyenDi.dispose();
					}else 
						JOptionPane.showMessageDialog(themHuongDanVienVaoChuyenDi, "H?????ng d???n vi??n ???? c?? l???ch tr??ng!");
						
				}else {
					JOptionPane.showMessageDialog(themHuongDanVienVaoChuyenDi, "Ch??a ch???n ?????a danh ????? th??m!");
				}
			}
		});
		main.add(them);
		themHuongDanVienVaoChuyenDi.setVisible(true);
	}
	private void themDuLieuMacDinh() {
		maLabel2.setText(chuyenDi.getMaChuyenDi());
		loaiChuyenDiboComboBox.setSelectedItem(chuyenDi.getLoaiChuyenDi().getTenLoai());
		ngayKhoiHanhDateChooser.setDate(chuyenDi.getNgayKhoiHanh());
		ngayKetThucDateChooser.setDate(chuyenDi.getNgayKetThuc());
		soChoSpinner.setValue(chuyenDi.getSoCho());
		noiKhoiHanhComboBox.setSelectedItem(chuyenDi.getNoiKhoiHanh().getTinhThanh());
		giaChuyenDiField.setText(chuyenDi.getGiaChuyenDi() + "");
		motaArea.setText(chuyenDi.getMoTa());
		tableModel.setRowCount(0);
		tableModel.addRow(new Object[] {chuyenDi.getHuongDanVien().getMaHuongDanVien(), chuyenDi.getHuongDanVien().getTenHuongDanVien(), chuyenDi.getHuongDanVien().isGioiTinh() ? "N???":"Nam"});
	}
}

