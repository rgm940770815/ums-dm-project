/**
 * ServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.net.withub.ums.webService;

public interface ServicePortType extends java.rmi.Remote {
    public java.lang.String getChildOrgInfoService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getChildDeptInfoService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getChildDeptInfoService1(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String getUserAllInfoForChildDeptService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeUserAllInfoForChildDeptService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getUserInfoForChildOrgService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getUserAllInfoForChildOrgServiceBysl(java.lang.String arg0, java.lang.String arg1, int arg2, int arg3) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeUserAllInfoForChildOrgService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeChildOrgInfoService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getUserInfoForChildDeptService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeChildDeptInfoService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getUserAllInfoForChildOrgService(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeUserInfoForChildOrgService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
    public java.lang.String getByTimeUserInfoForChildDeptService(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, cn.net.withub.ums.webService.UnsupportedEncodingException;
}
