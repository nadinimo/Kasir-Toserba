package toserbaBaru;
import java.util.Scanner;
public class ToserbaBaru {
    
	// Array untuk menyimpan data barang
	static String[][] dataBarang = {
		{"001", "Sapu", "15000"},
		{"002", "Kemoceng", "10000"},
		{"003", "Tempat Sampah", "12000"},
		{"004", "Baskom", "8000"}, 
		{"005", "Sikat Gigi", "6000"},  
		{"006", "Tempat Pensil", "9000"},      
		{"007", "Bolpoin", "3000"},      
		{"008", "Penghapus", "2000"},      
		{"009", "Gayung", "11000"},      
		{"011", "Rautan", "4000"},      
		{"012", "Lap Kanebo", "16000"},      
		{"013", "Pisau", "17000"},      
		{"014", "Payung", "27000"},   
		{"015", "Jas Hujan", "87000"}      
	};
	
    //Method untuk sorting data barang dari array
    static String[][] Sort(int[] jumlahTerjual) {
        for (int i = 0; i < jumlahTerjual.length; i++) {
			// percabangan if-else
			for(int j = i+1; j < jumlahTerjual.length; j++){
			    if(jumlahTerjual[i] < jumlahTerjual[j]){
			        int tmp = jumlahTerjual[i];
			        jumlahTerjual[i] = jumlahTerjual[j];
			        jumlahTerjual[j] = tmp;
			        String t = dataBarang[i][0];
    				dataBarang [i][0] = dataBarang[j][0];
    				dataBarang [j][0] = t;
    				
    				t = dataBarang[i][1];
    				dataBarang [i][1] = dataBarang[j][1];
    				dataBarang [j][1] = t;
    				
    				t = dataBarang[i][2];
    				dataBarang [i][2] = dataBarang[j][2];
    				dataBarang [j][2] = t;
			    }
			}
		}
		return dataBarang;
    }
        
    //Methode untuk searching data barang dari array
    static int searching(String[][] dataBarang, String kata, int idx) {
        //Looping for
        for (int i = 0; i < dataBarang.length; i++ ) {
            if(dataBarang[i][idx].indexOf(kata)>=0) {
                // Jika data ditemukan
                return i;
            }
        }
        // Jika data tidak ditemukan
        return -1; 
    }
        
	// Method untuk menambah data barang ke array
	static void tambahDataBarang(String[] data) {
		int panjang = dataBarang.length;
		String[][] temp = new String[panjang + 1][3];
		for (int i = 0; i < panjang; i++) {
			temp[i] = dataBarang[i];
		}
		temp[panjang] = data;
		dataBarang = temp;
	}

	// Method untuk menghapus data barang dari array
	static void hapusDataBarang(int index) {
		int panjang = dataBarang.length;
		String[][] temp = new String[panjang - 1][3];
		for (int i = 0; i < index; i++) {    
			temp[i] = dataBarang[i];
		}
		for (int i = index; i < panjang - 1; i++) {
			temp[i] = dataBarang[i + 1];
		}
		dataBarang = temp;
	}

	public static void main(String[] args) {     
		Scanner input = new Scanner(System.in);
		int user;
		int[] jumlahTerjual = new int[100];
		
		do{
			System.out.println("=================================================================================================");
			System.out.println("                   SELAMAT DATANG DI TOKO SERBA ADA (TOSERBA) RUNGKUT                            ");
			System.out.println("                              LENGKAP, MURAH, DAN RAMAH                                          ");
			System.out.println("=================================================================================================");
						
			System.out.println("====================== Silakan Pilih Menu User di bawah ini : ===================================");
			System.out.println("1. Kasir");
			System.out.println("2. Admin");
			System.out.println("3. Owner");
			System.out.println("4. Keluar");
			System.out.print("Pilih : ");
			user = input.nextInt();
			switch(user){
				case 1:{
					System.out.println("==================== ANDA ADALAH KASIR TOKO ============================================");
										
					// Menampilkan menu
					int fitur = 0;
					while (fitur != 3) {
						System.out.println("\nMenu Kasir Toko");
						System.out.println("1. Cari Barang");
						System.out.println("2. Tambah Transaksi");
						System.out.println("3. Keluar");
						System.out.print("Pilih menu: ");
						fitur = input.nextInt();

						if(fitur == 1){
							System.out.println("Masukkan Nama Barang yang Dicari: ");
                            String search = input.next();
                            System.out.println("");
                            System.out.println("==========Hasil Pencarian==========");
                            for (int i = 0; i < dataBarang.length; i++){
                                int indeks = searching(dataBarang, search, 1);
                                if(indeks != -1){
                                    System.out.println(indeks + "\t" + dataBarang[indeks][1] + "\t" + dataBarang[indeks][2]);
                                    break;
                                }
							}
						} else if(fitur == 2){
							int jumlahBarang = 0;
							int totalHarga = 0;
			
							System.out.print("\n Masukkan jumlah barang yang ingin dibeli:  ");                  
							jumlahBarang = input.nextInt();
							for (int i = 0; i < jumlahBarang; i++) {
								int indeksBarang = -1;
								System.out.print("Masukkan kode barang yang ingin dibeli: ");
							    input.nextLine();
    							String nomorBarang = input.nextLine();
    							while(indeksBarang == -1){
    							    // Mencari kode barang tersebut ada atau tidak
								    for(int j = 0; j < dataBarang.length; j++){
								        if(dataBarang[j][0].equals(nomorBarang)){
								            indeksBarang = j;
								            break;
								        }
								    }
								    if(indeksBarang == -1){
								        System.out.println("Kode barang yang dimasukkan salah");
								        System.out.print("Masukkan kode barang yang ingin dibeli: ");
    							        nomorBarang = input.nextLine();
								    }
    							}
								System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
								int jumlah = input.nextInt();
								totalHarga += Integer.parseInt(dataBarang[indeksBarang][2]) * jumlah;

								// Menambahkan jumlah barang yang dibeli
								jumlahTerjual[indeksBarang] += jumlah;
							}   
							System.out.println("\nTotal harga: Rp " + totalHarga);
							System.out.println(String.format("Total belanja: Rp.%,d", totalHarga));    
							System.out.print("Masukkan uang yang bayar: ");
							int uangBayar = input.nextInt();    
							int kembalian = uangBayar - totalHarga;
							System.out.println(String.format("Kembalian: Rp.%,d", kembalian));     
						} else if(fitur == 3){
							System.out.println("========= Anda telah Keluar User Kasir! ======================");   
						}
					}                                         
					break;
				}
																		
				case 2:{
					System.out.println("============ ANDA ADALAH ADMIN TOKO ============================");
										
					// Menampilkan menu
					int fitur2 = 0;
					while (fitur2 != 5) {
						System.out.println("\nMenu Admin Kasir");
						System.out.println("1. Tampilkan data barang");
						System.out.println("2. Tambah data barang");
						System.out.println("3. Ubah data barang");
						System.out.println("4. Hapus data barang");
						System.out.println("5. Keluar User Admin");
						System.out.print("Pilih menu: ");
						fitur2 = input.nextInt();

						if (fitur2 == 1) {
							// Menampilkan daftar barang
							System.out.println("Daftar Barang");
							System.out.println("Kode\tNama\tHarga");
							for (int i = 0; i < dataBarang.length; i++) {
								System.out.println(dataBarang[i][0] + "\t" + dataBarang[i][1] + "\t" + dataBarang[i][2]);
							}
						} else if (fitur2 == 2) {
							// Meminta user memasukkan data barang yang akan ditambahkan
							System.out.print("Masukkan kode barang: ");
							String kode = input.next();
							System.out.print("Masukkan nama barang: ");
							String nama = input.next();
							System.out.print("Masukkan harga barang: ");
							String harga = input.next();

							// Menambah data barang ke array
							String[] data = {kode, nama, harga};
							tambahDataBarang(data);
						} else if (fitur2 == 3) {
							// Meminta user memasukkan kode barang yang akan diubah
							System.out.print("Masukkan kode barang yang akan diubah: ");
							String kode = input.next();

							// Mencari data barang yang akan diubah
							int index = -1;
							for (int i = 0; i < dataBarang.length; i++) {
								if (dataBarang[i][0].equals(kode)) {
									index = i;
									break;
								}
							}

							// Jika barang ditemukan, meminta user memasukkan data barang yang baru dan mengubah data barang di array
							if (index != -1) {
								System.out.print("Masukkan kode barang baru: ");
								String kodeBaru = input.next();
								System.out.print("Masukkan nama barang baru: ");
								String namaBaru = input.next();
								System.out.print("Masukkan harga barang baru: ");
								String hargaBaru = input.next();
								dataBarang[index][0] = kodeBaru;
								dataBarang[index][1] = namaBaru;
								dataBarang[index][2] = hargaBaru;
								System.out.println("Data barang berhasil diubah.");
								
							} else {
							// Jika barang tidak ditemukan, menampilkan pesan error
								System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
							}
								
						} else if (fitur2 == 4) {
							// Meminta user memasukkan kode barang yang akan dihapus
							System.out.print("Masukkan kode barang yang akan dihapus: ");
							String kode = input.next();

							// Mencari data barang yang akan dihapus
							int index = -1;
							for (int i = 0; i < dataBarang.length; i++) {
								if (dataBarang[i][0].equals(kode)) {
									index = i;
									break;
								}
							}

							// Jika barang ditemukan, menghapus data barang dari array
							if (index != -1) {
								hapusDataBarang(index);
								System.out.println("Data barang berhasil dihapus.");
									
							} else {               
							// Jika barang tidak ditemukan, menampilkan pesan error
								System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
							}
									
						} else if(fitur2 == 5){
							System.out.println("========= Anda telah Keluar User Admin! ======================");
						} else {
							// Menampilkan pesan error jika menu yang dipilih tidak valid
							System.out.println("Menu tidak valid.");
						}
					} 
					break;				
				}
								
				case 3:{
					System.out.println("=============== ANDA ADALAH OWNER ============================== ");
										
					// Menampilkan menu
					int fitur3 = 0;
					while (fitur3 != 3) {
						System.out.println("\nMenu Owner Toko");
						System.out.println("1. Laporan Penjualan");
						System.out.println("2. Daftar Top 5 Barang Terlaris");
						System.out.println("3. Keluar");
						System.out.print("Pilih menu: ");
						fitur3 = input.nextInt();

						if (fitur3 == 1) {
							int totalPenjualan = 0;
							for (int i = 0; i < dataBarang.length; i++) {
							    totalPenjualan += jumlahTerjual[i];
							}
							System.out.println("===============");
							System.out.println("LAPORAN PENJUALAN");
							System.out.println("===============");
							System.out.println("Total penjualan: " + totalPenjualan);
							System.out.println("\nDetail penjualan:");
							for (int i = 0; i < dataBarang.length; i++) {
								System.out.println(dataBarang[i][1] + ": " + jumlahTerjual[i] + " buah");
							}
						} else if (fitur3 == 2) {
							int totalPenjualan = 0;
							System.out.println("===============");
							System.out.println("TOP 5 BARANG TERLARIS");
							System.out.println("===============");
							
							String[][] dataBarangUrut = new String[10][10];
							dataBarangUrut = Sort(jumlahTerjual);

							// Tampilkan hasil
							for (int i = 0; i < 5; i++) {
								System.out.println((i + 1) + ". " + dataBarang[i][1] + ": " + jumlahTerjual[i] + " buah");
							}
						} else if (fitur3 == 3) {
							System.out.println("========= Anda telah Keluar User Owner! ======================");
						}
					}
					break;
				}
					
				case 4: {
					System.out.println("========= Terima Kasih, Anda telah Keluar! ======================");
					break;
				}
			} 
		} while(user!=4);
	}
}
