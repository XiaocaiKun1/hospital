    @FXML
    public void bt_start() throws Exception {
        String sql = "update projects set jiancha_state='妫鏌ュ畬鎴' where reg_id= " + GlobalData.register_id_Selected + " and pro_id=" + project_id;