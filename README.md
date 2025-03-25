# ChatApp
## Git
* Tạo Folder dự án **BlueOcean** (Không đề xuất )
* Mở git bash trong Folder
### Nếu chưa tạo thư mục BlueOcean ( clone sẽ tự khởi tạo thư mục BlueOcean) -Reccommen
```bash
git clone https://github.com/TruongNguyenDinh/BlueOcean
```
### Nếu đã tạo thư mục trống BlueOcean
```bash
git init
git remote add origin https://github.com/TruongNguyenDinh/BlueOcean.git
git pull origin main
```
## Tạo nhánh
```bash
git branch ten_nhanh_moi
```
## Chuyển nhánh
```bash
git switch ten-nhanh
```
## Đồng bộ 
* Trường hợp trên **repo** có nhánh mới nhưng trên local chưa có
```bash
git fetch origin
```
*Kiểm tra cách nhánh hiện có
```bash
git branch -r
```
* Lệnh này sẽ tạo một nhánh mới tên **nhanh-moi** trên máy local và liên kết nó với **origin/nhanh-moi**
```bash
git checkout -b nhanh-moi origin/nhanh-moi
```
* Kiểm tra lại
```bash
git branch
```
* Cập nhật code mới nhất từ GitHub bằng
```bash
git pull origin nhanh-moi( nhánh cần lấy code)
```

