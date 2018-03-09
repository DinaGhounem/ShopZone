package jtech.shopzone.view.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Mahrous
 */
public class FileUploadAdapter {
    private List<FileItem> parameters;
    private List<FileItem> files;

    public FileUploadAdapter(HttpServletRequest httpServletRequest) {
        parameters = new ArrayList<>();
        files = new ArrayList<>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> items = servletFileUpload.parseRequest(httpServletRequest);
            for (FileItem item : items) {
                if(item.isFormField())
                {
                    parameters.add(item);
                }
                else
                {
                    files.add(item);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    public String getParameter(String parameter)
    {
        String parameterValue = null;
        for (FileItem item : parameters)
        {
            if (item.getFieldName().equals(parameter))
                parameterValue = item.getString();
        }
        return  parameterValue;
    }

    public FileItem getFile(String fileName)
    {
        FileItem myfile = null;
        for (FileItem item : files)
        {
            if (item.getFieldName().equals(fileName))
                myfile = item;
        }
        return myfile;
    }

    public boolean writeToFolder(String fileName, String folderRelativePath, ServletContext servletContext, FileItem item)
    {
        boolean written = true;
        try {
            String rootPath = servletContext.getRealPath(folderRelativePath);
            String fullPath = rootPath+"/"+item.getName();
            item.write(new File(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
            written = false;
        }

        return  written;
    }
}