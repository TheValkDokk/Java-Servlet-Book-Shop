<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div class="modal-dialog">
    <div class="modal-content">
        <form>
            <div class="modal-header">						
                <h4 class="modal-title">Edit Book</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">					
                <div class="form-group">
                    <label>Book ID</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Book Name</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <textarea class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Available</label>
                    <input type="checkbox" class="form-control">
                </div>
                <div class="form-group">
                    <label>Image</label>
                    <input type="text" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Published</label>
                    <input type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Writer</label>
                    <input type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Cover</label>
                    <input type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label>Penciler</label>
                    <input type="text" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                <input onclick="loadEdit()" type="submit" class="btn btn-info" value="Save">
            </div>
        </form>
    </div>
</div>