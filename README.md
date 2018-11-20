#### TRƯỜNG ĐẠI HỌC BÁCH KHOA TP HCM

#### KHOA KHOA HỌC VÀ KỸ THUẬT MÁY TÍNH

#### - - - - - - - - - - - -

## MẠNG MÁY TÍNH (CO3003)

## Bài tập lớn 1

# Simple Chat Application

```
GVHD: Nguyễn Hồng Nam
```
```
SV thực hiện: 
* Hồ Quang Toàn 1613594
* Nguyễn Việt Hưng 1611441
* Đặng Tuấn Vũ 1614150
* Nguyễn Hoàng Bảo Phương 1612698
```
```
Tp. Hồ Chí Minh, Tháng 10/2018
```

## Mục lục


- 1 Giới thiệu ứng dụng
   - 1.1 Login
   - 1.2 Chat riêng tư
   - 1.3 Truyền gửi file
- 2 Định nghĩa giao thức cho từng chức năng
- 3 Thiết kế ứng dụng
   - 3.1 Công nghệ sử dụng
   - 3.2 Kiến trúc ứng dụng
   - 3.3 Các class chính
- 4 Đánh giá kết quả hiện thực
   - 4.1 Kết quả đạt được
   - 4.2 Những điều chưa đạt được
- 5 Tài liệu hướng dẫn sử dụng
   - 5.1 Giới thiệu
   - 5.2 Server
   - 5.3 Client
- 1 Flowchart của ứng dụng Danh sách hình vẽ
- 2 Class Diagram của ứng dụng
- 3 Server chưa được khởi tạo, giá trị của STATUS là OFF.
- 4 Server sau khi khởi tạo, giá trị của Status đã chuyển sang RUNNING
- 5 Server hoạt động bình thường với 4 user online
- 6 Server hoạt động bình thường
- 7 Lỗi do server chưa được khởi tạo
- 8 Giao diện chính của phần mềm
- 9 Giao diện khung xác nhận
- 10 Giao diện khung trò chuyện
- 11 Cuộc trò chuyện giữa 2 người
- 12 Cuộc trò chuyện giữa 3 người
- 13 Giao diện khung xác nhận nhận file
- 14 Giao diện màn hình người gửi sau khi gửi file
- 15 Chọn thư mục lưu file
- 16 Giao diện khung chat khi nhận file thành công
- 17 Giao diện thông báo khi chọn kết thúc
- 18 Giao diện thông báo cho người nhận


## 1 Giới thiệu ứng dụng

Ứng dụng là phần mềm cho phép hai hay nhiều người dùng có thể giao tiếp với nhau với các
tính năng chính: login, tạo chat riêng tư, truyền gửi file trong lúc chat.

### 1.1 Login

Chức năng này cho phép người dùng đăng nhập vào ứng dụng khi biết IP và port của server.

### 1.2 Chat riêng tư

Chức năng này cho phép user tạo hội thoại riêng tư và gửi tin trực tiếp tới user khác. Một user
có thể nhắn tin trực tiếp với nhiều user trong cùng một thời điểm.

### 1.3 Truyền gửi file

Chức năng này cho phép user gửi và nhận một số file với định dạng và kích thước được quy định
trước bởi nhà phát triển trong chat riêng tư với user khác khi được cho phép.


## 2 Định nghĩa giao thức cho từng chức năng

```
Nội dung Mục đích Ghi chú
‘ <SESSION_REQ> clientID Yêu cầu tạo một chat Sử dụng 1 lần cho
</SESSION_REQ> session từ client. clientID mỗi chat session
là id của người dùng
<PEER_NAME> userName Tạo user khi người dùng userName là tên người dùng
</PEER_NAME> đăng nhập vào server
<PORT> numPort Tạo port cho user numPort là số hiệu port
</PORT>
<SESSION_KEEP_ALIVE> Nội dung request User request 10s/lần bao gồm
</SESSION_KEEP_ALIVE> do user gửi lên server tên user và trạng thái
<STATUS> statusUser Thông báo trạng thái user statusUser là trạng thái user
</STATUS>
<SESSION_DENY /> Từ chối người dùng Từ chối sau khi
kết nối tới server người dùng login
<SESSION_ACCEPT> Chấp nhận gười dùng Chấp nhận sau khi
</SESSION_ACCEPT> kết nối tới server người dùng login
<CHAT_REQ> userName Yêu cầu tạo chat riêng tư userName là tên user tạo
</CHAT_REQ> từ một user với user khác yêu cầu chat riêng tư
<IP> stringIP </IP> Định danh IP cho user stringIP là IP của user
<CHAT_DENY /> Từ chối tạo chat session Từ chối sau khi có
yêu cầu tạo chat riêng tư
<CHAT_ACCEPT /> Chấp nhận tạo chat session Chấp nhận sau khi có
yêu cầu tạo chat riêng tư
<CHAT_MSG> Message Gửi nội dung chat Message được gửi khi
</CHAT_MSG> user đồng ý tạo chat riêng tư
<PEER> peerData Xác định peer của user peerData là peer của user
</PEER> do server trả về gồm name, port, IP
<FILE_REQ> fileName Yêu cầu gửi file fileName là tên file muốn gửi
</FILE_REQ>
<FILE_REQ_NOACK /> Từ chối yêu cầu gửi file Sử dụng sau khi
có yêu cầu gửi file
<FILE_REQ_ACK> Chấp nhận yêu cầu gửi file Sử dụng sau khi
</FILE_REQ_ACK> có yêu cầu gửi file
<FILE_DATA_BEGIN /> Bắt đầu gửi file Quá trình gửi file sau khi
người gửi và nhận đồng ý
<FILE_DATA> fileData Nội dung file cần chuyển
</FILE_DATA>
<FILE_DATA_END /> Kết thúc quá trình gửi file Yêu cầu bởi người gửi file
<CHAT_CLOSE /> Kết thúc chat riêng tư Sử dụng sau khi
tạo chat riêng tư
MAX_MSG_SIZE 102400 Kích thước tối đa của
một message(file) trong chat
SERVER_ONLINE "RUNNING" User đang online
SERVER_OFFLINE "STOP" User thoát khỏi chat session
```

## 3 Thiết kế ứng dụng

### 3.1 Công nghệ sử dụng

```
TCP Socket: Một kĩ thuật dùng để hỗ trợ lập trình các ứng dụng giao tiếp qua mạng. TCP
Socket sử dụng Stream để thực hiện quá trình truyền dữ liệu của hai máy tính đã thiết lập
kết nối.
```
```
Java Swing: Là một phần của Java Foundation Classes (JFC) được sử dụng để tạo các ứng
dụng Window-Based.
```
### 3.2 Kiến trúc ứng dụng

### Form: Ứng dụng gồm bốn form chính:

```
Server form: Form quản lí server, hiển thị thông tin server, các request user gửi lên.
```
```
Login form: Form đăng nhập vào server. Để kết nối được, user phải cung cấp username
chưa có ai đăng kí trước đó với server.
```
```
Main form: Form để quản lí danh sách những người dùng khác đang online cùng với user
hiện tại. User có thể chọn bất kì ai đang online để bắt đầu một cuộc trò chuyện.
```
```
Chat form: Khi hai user đã chấp nhận trò chuyện với nhau thì form này sẽ xuất hiện. Hiển
thị thông tin cuộc trò chuyện giữa hai người.
```
### Flow Chart

```
Hình 1:Flowchart của ứng dụng
```

### 3.3 Các class chính

```
Hình 2:Class Diagram của ứng dụng
```
### Class cho server

```
ServerGUI.java: sử dụng để thao tác với server như bật, tắt server. Ngoài ra còn hiển thị
các thông tin server như: IP, Port, số lượng người kết nối, các protocol mà client gửi lên.
```
```
ServerCore.java: Đây là class để quản lí server, gồm đầy đủ thông tin, chức năng chính của
server:
```
- Tạo một SocketServer.
- Quản lí danh sách các user đang online
- Tạo một thread là WaitForConnect để xử lý các request kết nối từ user. Có thể là yêu
    cầu dăng nhập, thoát ứng dụng, lấy thông tin user đang online. Nếu đăng nhập thành
    công, trả về cho client danh sách user đang online, nếu không sẽ trả về một protocol
    từ chối đăng nhập.

### Class cho client

```
MainGUI.java: GUI class để hiển thị danh sách user khác đang online, bắt sự kiện gửi yêu
cầu chat của user.
```

```
ChatGUI.java: Xử lí việc chat, gửi nhận File giữa hai user. Hiển thị nội dung chat giữa hai
user.
```
```
ClientServer.java: Tạo và quản lí ServerSocket của mỗi user. Xử lí request chat gửi đến từ
user khác.
```
```
Client.java: Quản lí thông tin hiện tại của user. Nó sẽ tạo một thread để liên tục gửi request
tới server để cập nhật danh sách user. Ngoài ra nó còn xử lí request chat tới user khác.
```
### Các class khác

```
DataFile.java: Tạo một đối tượng File để gửi đi.
```
```
Peer.java: Tạo một đối tượng Peer có các thuộc tính: IP, port, name.
```
```
Tags.java: Định nghĩa các protocol được sử dụng trong ứng dụng.
```
```
Encode.java: Định nghĩa các phương thức user đùng để gửi request lên server.
```
```
Dedode.java: Giải mã các phương thức để lấy thông tin user, port hay ip...
```

## 4 Đánh giá kết quả hiện thực

### 4.1 Kết quả đạt được

```
Ứng dụng được xây dựng dựa trên mô hình kết hợp giữa client-server cho việc quản lí các
user và P2P cho việc trò chuyện giữa hai user với nhau.
```
```
Ứng dụng có các tính năng đơn giản như: chat giữa hai user, một lúc đồng thời chat với
nhiều user, gửi File trong quá trình chat.
```
### 4.2 Những điều chưa đạt được

```
Mã nguồn còn chưa tối ưu cho ứng dụng.
```
```
Ứng dụng còn có thể thêm các tính năng như: chat nhóm, gọi video...
```

## 5 Tài liệu hướng dẫn sử dụng

### 5.1 Giới thiệu

Ứng dụng chat giữa các máy tính trong cùng mạng với nhau được viết bằng Java. Thông qua
ứng dụng, người dùng có thể trò chuyện với một hoặc nhiều người trong cùng một thời điểm.
Ngoài ra phần mềm còn có các tính năng như: gửi icon, gửi file trong chat riêng tư, hiện thị danh
sách các tài khoản đang hoạt động...

### 5.2 Server

Ta cần khởi tạo server để người dùng có thể chat online. Cửa sổ Server Managerment sẽ gồm
các giá trị:

```
IP ADDRESS: địa chỉ IP của server được khởi tạo.
```
```
PORT: cổng được server dùng trong suốt quá trình hoạt động (mặc định là 8080)
```
```
STATUS: tình trạng hiện tại của server (RUNNING/OFF)
```
```
USER ONLINE: số user đang kết nối đến server.
```
```
LOG: lịch sử quá trình user tương tác với server.
```
```
Hình 3:Server chưa được khởi tạo, giá trị của STATUS là OFF.
```

```
Hình 4:Server sau khi khởi tạo, giá trị của Status đã chuyển sang RUNNING
```
```
Hình 5:Server hoạt động bình thường với 4 user online
```
### 5.3 Client

Màn hình đăng nhập bao gồm:


IP Server: Địa chỉ IP của server

Port Server: Cổng server mở kết nối (mặc định 8080)

User Name: Tên người dùng để thực hiện trò chuyện

```
Hình 6:Server hoạt động bình thường
```
Các nguyên nhân có thể gây ra lỗi khi đăng nhập:

Tên đăng nhập chứa ký tự không hợp lệ (tên không bắt đầu bằng số, chỉ chứa kí tự _)

Server chưa bật

Tên đăng nhập đã được sử dụng

```
Hình 7:Lỗi do server chưa được khởi tạo
```
Danh sách các tài khoản đang hoạt động sẽ tự động cập nhật trong thời gian là 10s


```
Hình 8:Giao diện chính của phần mềm
```
Chọn tên người dùng trong danh sách các tài khoản đang hoạt động để có thể bắt đầu trò
chuyện.
Để đảm bảo tính riêng tư, người dùng được chọn cần xác nhận để có thể bắt đầu cuộc trò chuyện.

```
Hình 9:Giao diện khung xác nhận
```

```
Hình 10:Giao diện khung trò chuyện
```
Mô tả quá trình chat giữa 2 người.

```
Hình 11:Cuộc trò chuyện giữa 2 người
```
Quá trình chat với nhiều người cùng một lúc.


```
Hình 12:Cuộc trò chuyện giữa 3 người
```
Người dùng có thể gửi file trong trò chuyện. Sau khi chọn file gửi, một thông báo gửi đến và
cần xác nhận từ người nhận để thực hiện quá trình gửi file.

```
Hình 13:Giao diện khung xác nhận nhận file
```
```
Sau khi được chấp nhận, quá trình gửi file sẽ được diễn ra.
```

```
Hình 14:Giao diện màn hình người gửi sau khi gửi file
```
Người nhận chọn thư mục lưu file sau khi file được gửi thành công.

```
Hình 15:Chọn thư mục lưu file
```

```
Hình 16:Giao diện khung chat khi nhận file thành công
```
Để kết thúc trò chuyện bạn có thể chọn LEAVE CHAT trong khung trò chuyện

```
Hình 17:Giao diện thông báo khi chọn kết thúc
```
Người nhận cũng nhận được thông báo khi đối phương xác nhận kết thúc trò chuyện

```
Hình 18:Giao diện thông báo cho người nhận
```

