/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.util.bin.format.macho.dyld;

import java.io.IOException;

import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;
import ghidra.app.util.bin.format.macho.MachConstants;
import ghidra.program.model.data.*;
import ghidra.util.exception.DuplicateNameException;

/**
 * Represents a dyld_cache_image_info_extra structure.
 * 
 * @see <a href="https://opensource.apple.com/source/dyld/dyld-625.13/launch-cache/dyld_cache_format.h.auto.html">launch-cache/dyld_cache_format.h</a> 
 */
@SuppressWarnings("unused")
public class DyldCacheImageInfoExtra implements StructConverter {

	private long exportsTrieAddr;
	private long weakBindingsAddr;
	private int exportsTrieSize;
	private int weakBindingsSize;
	private int dependentsStartArrayIndex;
	private int reExportsStartArrayIndex;

	/**
	 * Create a new {@link DyldCacheImageInfoExtra}.
	 * 
	 * @param reader A {@link BinaryReader} positioned at the start of a DYLD image info extra
	 * @throws IOException if there was an IO-related problem creating the DYLD image info extra
	 */
	public DyldCacheImageInfoExtra(BinaryReader reader) throws IOException {
		exportsTrieAddr = reader.readNextLong();
		weakBindingsAddr = reader.readNextLong();
		exportsTrieSize = reader.readNextInt();
		weakBindingsSize = reader.readNextInt();
		dependentsStartArrayIndex = reader.readNextInt();
		reExportsStartArrayIndex = reader.readNextInt();
	}

	@Override
	public DataType toDataType() throws DuplicateNameException, IOException {
		StructureDataType struct = new StructureDataType("dyld_cache_image_info_extra", 0);
		struct.add(QWORD, "exportsTrieAddr", "");
		struct.add(QWORD, "weakBindingsAddr", "");
		struct.add(DWORD, "exportsTrieSize", "");
		struct.add(DWORD, "weakBindingsSize", "");
		struct.add(DWORD, "dependentsStartArrayIndex", "");
		struct.add(DWORD, "reExportsStartArrayIndex", "");
		struct.setCategoryPath(new CategoryPath(MachConstants.DATA_TYPE_CATEGORY));
		return struct;
	}
}
