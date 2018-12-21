$('#VanzoData').bootstrapTable({
    ajax : function (request) {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/xhs/b06/device",
        contentType: "application/json;charset=utf-8",
        data: '',

        success: function (msg) {
            request.success({
                row: msg
            });
            $('#VanzoData').bootstrapTable('load', msg);
        },
        error: function () {
            alert("错误");
        }
    });
},
striped: true,  //表格显示条纹
    pagination: true, //启动分页
    pageSize: 10,  //每页显示的记录数
    pageNumber:1, //当前第几页
    pageList: [5, 10, 15, 20, 25],  //记录数可选列表
    search: false,  //是否启用查询
    showColumns: true,  //显示下拉框勾选要显示的列
    showRefresh: true,  //显示刷新按钮
    columns: [{
    field: 'Id',
    title: 'id'
}, {
    field: 'DeviceId',
    title: '设备编号'
}, {
    field: 'StepCount',
    title: '步数统计'
}, {
    field: 'SignalIntensity',
    title: '信号强度'
}, {
    field: 'Longitude',
    title: '经度'
}, {
    field: 'Latitude',
    title: '纬度'
}, {
    field: 'UploadTime',
    title: '上传时间'
}, {
    field: 'UpdateTime',
    title: '更新时间'
}
]
});