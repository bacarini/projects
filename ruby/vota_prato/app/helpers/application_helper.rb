module ApplicationHelper
	def menu_principal
		menu = %w(cliente qualificacao restaurante comentario)
		menu_principal = "<ul>"
		menu.each do |item|
		  menu_principal << "<li>" + link_to(item, :controller => item.pluralize) + "</li>"
		end
		menu_principal << "</ul>"
		raw menu_principal
	end

	def valor_formatado(number)
	  number_to_currency(number, :unit => "R$", :separator => ",", :delimiter => ".")
	end

	def comentarios(comentavel)
		comentarios = "<div class='comentario'>"
		comentarios << "<b>Comentarios:</b>"
		comentarios << render(:partial => "comentarios/comentario",
                        :collection => comentavel.comentarios) unless comentavel.comentarios.empty?

				   	
		comentarios << "</div>"	
		raw comentarios
	end

	def novo_comentario(comentavel)
		raw render(:partial => "comentarios/novo_comentario",
		                  :locals => { :comentavel => comentavel })
	end

end
