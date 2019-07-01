PROJECT_ROOT := $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
INCLUDES = -I $(PROJECT_ROOT)include
CPPFLAGS += "-Wall" "-Wextra" "-std=c++17"
OBJS = Prog4_3.o DequeTemplate.o Deque.o main.o

DEPDIR := .d
$(shell mkdir -p $(DEPDIR) >/dev/null)
DEPFLAGS = -MT $@ -MMD -MP -MF $(DEPDIR)/$*.Td
POSTCOMPILE = @mv -f $(DEPDIR)/$*.Td $(DEPDIR)/$*.d && touch $@


ifndef BUILD_MODE
BUILD_MODE = run
endif

ifeq ($(BUILD_MODE),debug)
CPPFLAGS += "-gdwarf-2"
else 
ifeq ($(BUILD_MODE),run)
CPPFLAGS += -O2
else
$(error Build mode $(BUILD_MODE) not supported by this Makefile)
endif
endif

COMPILE.c = $(CC) $(DEPFLAGS) $(DEPFLAGS) $(CFLAGS) $(CPPFLAGS) $(INCLUDES) $(TARGET_ARCH) -c
COMPILE.cc = $(CXX) $(DEPFLAGS) $(CXXFLAGS) $(CPPFLAGS) $(INCLUDES) $(TARGET_ARCH) -c

all: Prog4_3


Prog4_3:	$(OBJS)
	$(CXX) -o $@ $(OBJS)


#Prog4_1.o: /Users/jannik/Documents/workspaceC++/Prog4_1//src/Prog4_1.cpp \
  /Users/jannik/Documents/workspaceC++/Prog4_1/includes/student.hpp 
$(DEPDIR)/%.d: ;
.PRECIOUS: $(DEPDIR)/%.d

-include $(DEPDIR)/*.d

%.o : $(PROJECT_ROOT)/src/%.cpp
%.o : $(PROJECT_ROOT)/src/%.cpp $(DEPDIR)/%.d
	$(COMPILE.cc) $(OUTPUT_OPTION) $<
	$(POSTCOMPILE)
	
.PHONY : clean
clean :
	-rm *