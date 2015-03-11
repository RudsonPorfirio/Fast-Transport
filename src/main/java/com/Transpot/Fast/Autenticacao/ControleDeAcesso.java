package com.Transpot.Fast.Autenticacao;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames={"Faces Servlet"})
public class ControleDeAcesso
  implements Filter
{
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest req = (HttpServletRequest)request;
    HttpSession session = req.getSession();
    if ((session.getAttribute("AdminLogado") != null) || 
      (session.getAttribute("UsuarioLogado") != null) || 
      (session.getAttribute("MotoristaLogado") != null) || 
      (req.getRequestURI().endsWith("Login.xhtml")) || 
      (req.getRequestURI().contains("javax.faces.resource/"))||
      (req.getRequestURI().endsWith("WebService.xhtml")) || 
      (req.getRequestURI().endsWith("CadastroMotorista.xhtml")) || 
      (req.getRequestURI().endsWith("CadastroUsuario.xhtml")))
    {
      String tipo =""+ session.getAttribute("tipo");
      if (((req.getRequestURI().contains("/motorista/")) || 
        (req.getRequestURI().contains("/usuario/"))) && 
        (tipo.equals("admin")))
      {
        req.getRequestURI().replace("/motorista/", "/admin/");
        
        System.out.println("entrou na condição");
        redireciona("/Fast-Transport/admin/Principal.xhtml", response);
      }
      if (((req.getRequestURI().contains("/motorista/")) || 
        (req.getRequestURI().contains("/admin/"))) && 
        (tipo.equals("usuario")))
      {
        req.getRequestURI().replace("/motorista/", "/usuario/");
        
        System.out.println("entrou na condição");
        redireciona("/Fast-Transport/usuario/Principal.xhtml", response);
      }
      if (((req.getRequestURI().contains("/usuario/")) || 
        (req.getRequestURI().contains("/admin/"))) && 
        (tipo.equals("motorista")))
      {
        req.getRequestURI().replace("/motorista/", "/usuario/");
        
        System.out.println("entrou na condição");
        redireciona("/Fast-Transport/motorista/Principal.xhtml", 
          response);
      }
      if ((req.getRequestURI().endsWith("Login.xhtml")) && 
        (tipo.equals("motorista"))) {
        redireciona("/Fast-Transport/motorista/Principal.xhtml", 
          response);
      }
      if ((req.getRequestURI().endsWith("Login.xhtml")) && 
        (tipo.equals("usuario"))) {
        redireciona("/Fast-Transport/usuario/Principal.xhtml", response);
      }
      if ((req.getRequestURI().endsWith("Login.xhtml")) && 
        (tipo.equals("admin"))) {
        redireciona("/Fast-Transport/admin/Principal.xhtml", response);
      }
      chain.doFilter(request, response);
    }
    else
    {
      redireciona("Login.xhtml", response);
    }
  }
  
  public void init(FilterConfig filterConfig)
    throws ServletException
  {}
  
  public void destroy() {}
  
  private void redireciona(String url, ServletResponse response)
    throws IOException
  {
    HttpServletResponse res = (HttpServletResponse)response;
    res.sendRedirect(url);
  }
}