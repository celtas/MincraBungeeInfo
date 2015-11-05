package mincrabungeeinfo;

import java.util.List;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.Protocol;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;


public class EventListener implements Listener {
	Plugin plugin;
	String version;
	List<String> hostaddress;
	int protocolversion;
	 public EventListener(Plugin _plugin) {
		plugin=_plugin;
		version=MincraBungeeInfo.config.getString("version");
		protocolversion=MincraBungeeInfo.config.getInt("protocolversion");
		hostaddress=MincraBungeeInfo.config.getStringList("hostaddress");
	}
	@EventHandler
	    public void onProxyPing(ProxyPingEvent event){
			if(hostaddress.contains(event.getConnection().getAddress().getHostName())){
		        ServerPing ping = event.getResponse();
		        Protocol protocol = new Protocol(version,protocolversion);
		        ping.setVersion(protocol);
		        event.setResponse(ping);
			}
	    }
}